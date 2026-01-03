package projeto_base_de_telas_e_login.controllers.web.TelaProdutos;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.domain.product.Product;
import projeto_base_de_telas_e_login.domain.product.ProductRequestDTO;
import projeto_base_de_telas_e_login.repositores.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class TelaProdutos {

    @Autowired
    private ProductRepository productRepository;

    // 🚀 Permitir USER e ADMIN
    @PostMapping("/add_products")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductRequestDTO body) {
        Product newProduct = new Product(body);
        this.productRepository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    // Somente ADMIN pode deletar produtos (opcional)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(Math.toIntExact(id))) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(Math.toIntExact(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<ProductRequestDTO>> getAllProducts() {
        List<ProductRequestDTO> productList = productRepository.findAll()
                .stream()
                .map(ProductRequestDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(productList);
    }
}
