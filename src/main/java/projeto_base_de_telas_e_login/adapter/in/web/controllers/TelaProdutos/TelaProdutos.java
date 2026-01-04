package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaProdutos;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.domain.UseCase.Produto.ProdutoUseCase;
import projeto_base_de_telas_e_login.domain.model.product.Product;
import projeto_base_de_telas_e_login.domain.model.product.DTO.ProductRequestDTO;
import projeto_base_de_telas_e_login.tudo.repositores.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class TelaProdutos {

    @Autowired
    private ProdutoUseCase produtoUseCase;

    @PostMapping("/add_products")
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        produtoUseCase.save(product);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        produtoUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(produtoUseCase.findAll());
    }
}
