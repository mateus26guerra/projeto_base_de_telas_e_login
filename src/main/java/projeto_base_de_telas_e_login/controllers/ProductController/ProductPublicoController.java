package projeto_base_de_telas_e_login.controllers.ProductController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_base_de_telas_e_login.domain.product.ProductRequestDTO;
import projeto_base_de_telas_e_login.repositores.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("productsPublico")
public class ProductPublicoController {


    @Autowired
    private ProductRepository productRepository;



    @GetMapping("/testeDeApiAberta")
    public String Teste(){
        return  "esse  Get esta aberto ao publico";
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductRequestDTO>> getAllProducts() {
        List<ProductRequestDTO> productList = productRepository.findAll()
                .stream()
                .map(ProductRequestDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(productList);
    }
}
