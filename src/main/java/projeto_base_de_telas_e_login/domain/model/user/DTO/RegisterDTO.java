package projeto_base_de_telas_e_login.domain.model.user.DTO;

import projeto_base_de_telas_e_login.domain.model.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}
