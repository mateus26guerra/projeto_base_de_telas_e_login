package projeto_base_de_telas_e_login.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_base_de_telas_e_login.domain.model.product.Product;

public interface ProdutoJpaRepository extends JpaRepository<Product, Integer> {
}
