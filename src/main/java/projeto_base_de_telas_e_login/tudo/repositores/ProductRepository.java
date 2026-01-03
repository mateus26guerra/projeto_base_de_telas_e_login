package projeto_base_de_telas_e_login.tudo.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_base_de_telas_e_login.domain.model.product.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
