package projeto_base_de_telas_e_login.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_base_de_telas_e_login.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
