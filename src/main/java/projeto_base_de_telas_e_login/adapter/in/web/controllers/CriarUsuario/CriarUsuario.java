package projeto_base_de_telas_e_login.adapter.in.web.controllers.CriarUsuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.domain.model.user.DTO.LoginResponseDTO;
import projeto_base_de_telas_e_login.domain.model.user.DTO.RegisterDTO;
import projeto_base_de_telas_e_login.domain.model.user.User;
import projeto_base_de_telas_e_login.domain.model.user.UserRole;
import projeto_base_de_telas_e_login.tudo.security.TokenService;
import projeto_base_de_telas_e_login.tudo.repositores.UserRepository;

import projeto_base_de_telas_e_login.domain.model.user.DTO.UpdateUserDTO;
import projeto_base_de_telas_e_login.domain.model.user.DTO.UserResponseDTO;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class CriarUsuario {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserResponseDTO>> listarUsuarios() {

        var users = userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getLogin(),
                        user.getRole().name()
                ))
                .toList();

        return ResponseEntity.ok(users);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/users/{id}")
    public ResponseEntity<?> atualizarUsuario(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserDTO data
    ) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setLogin(data.login());

        if (data.password() != null && !data.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(data.password()));
        }

        if (data.role() != null) {
            user.setRole(UserRole.valueOf(data.role()));
        }

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid RegisterDTO data) {

        if (userRepository.findByUsername(data.login()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());

        User newUser = new User(
                data.login(),
                encryptedPassword,
                data.role()
        );

        userRepository.save(newUser);

        var token = tokenService.generateToken(newUser);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
