package projeto_base_de_telas_e_login.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_base_de_telas_e_login.domain.user.AuthenticationDTO;
import projeto_base_de_telas_e_login.domain.user.LoginResponseDTO;
import projeto_base_de_telas_e_login.domain.user.RegisterDTO;
import projeto_base_de_telas_e_login.domain.user.User;
import projeto_base_de_telas_e_login.infra.security.TokenService;
import projeto_base_de_telas_e_login.repositores.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        System.out.println(" Login com sucesso de: ");
        return ResponseEntity.ok(new LoginResponseDTO(token));



    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByUsername(data.login()).isPresent())
            return ResponseEntity.badRequest().body("Usuário já existe");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        var token = tokenService.generateToken(newUser);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
