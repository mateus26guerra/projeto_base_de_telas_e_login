package projeto_base_de_telas_e_login.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.domain.product.Product;
import projeto_base_de_telas_e_login.domain.product.ProductRequestDTO;
import projeto_base_de_telas_e_login.repositores.ProductRepository;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("products") // 🔁 era "login"
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductRequestDTO body) {
        Product newProduct = new Product(body);
        this.productRepository.save(newProduct);
        return ResponseEntity.ok().build();
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

