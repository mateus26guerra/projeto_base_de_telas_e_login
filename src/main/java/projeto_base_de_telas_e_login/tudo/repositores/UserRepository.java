package projeto_base_de_telas_e_login.tudo.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_base_de_telas_e_login.domain.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
}

