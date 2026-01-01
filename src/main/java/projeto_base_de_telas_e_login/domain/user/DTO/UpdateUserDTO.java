package projeto_base_de_telas_e_login.domain.user.DTO;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(@NotBlank String login, String password, String role  ) {

}
