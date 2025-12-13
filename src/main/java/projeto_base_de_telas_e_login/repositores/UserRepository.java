package projeto_base_de_telas_e_login.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_base_de_telas_e_login.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

}
