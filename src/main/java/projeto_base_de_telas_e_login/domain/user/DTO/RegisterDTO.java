package projeto_base_de_telas_e_login.domain.user.DTO;

import projeto_base_de_telas_e_login.domain.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}
