package projeto_base_de_telas_e_login.adapter.out.persistence;

import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.domain.model.product.Product;
import projeto_base_de_telas_e_login.domain.repository.porta.ProdutoPorta;

import java.util.List;

@Component
public class ProdutoJpaAdapter implements ProdutoPorta {

    private final ProdutoJpaRepository repository;

    public ProdutoJpaAdapter(ProdutoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
