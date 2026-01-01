package projeto_base_de_telas_e_login.controllers.AuthenticationController.AuthenticationPrivateController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.domain.user.DTO.UpdateUserDTO;
import projeto_base_de_telas_e_login.domain.user.DTO.UserResponseDTO;
import projeto_base_de_telas_e_login.domain.user.UserRole;
import projeto_base_de_telas_e_login.repositores.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AuthenticationPrivateAdmController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
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

    @PutMapping("/users/{id}")
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
}
