package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaLogin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_base_de_telas_e_login.domain.model.user.DTO.AuthenticationDTO;
import projeto_base_de_telas_e_login.domain.model.user.DTO.LoginResponseDTO;
import projeto_base_de_telas_e_login.domain.model.user.User;
import projeto_base_de_telas_e_login.tudo.security.TokenService;
import projeto_base_de_telas_e_login.tudo.repositores.UserRepository;

@RestController
@RequestMapping("/auth")
public class TelaLogin {

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

}
