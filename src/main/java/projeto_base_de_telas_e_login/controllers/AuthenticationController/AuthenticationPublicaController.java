package projeto_base_de_telas_e_login.controllers.AuthenticationController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.domain.user.DTO.AuthenticationDTO;
import projeto_base_de_telas_e_login.domain.user.DTO.LoginResponseDTO;
import projeto_base_de_telas_e_login.domain.user.DTO.RegisterDTO;
import projeto_base_de_telas_e_login.domain.user.User;
import projeto_base_de_telas_e_login.infra.security.TokenService;
import projeto_base_de_telas_e_login.repositores.UserRepository;


@RestController
@RequestMapping("/auth")
public class AuthenticationPublicaController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword =
                new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
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
