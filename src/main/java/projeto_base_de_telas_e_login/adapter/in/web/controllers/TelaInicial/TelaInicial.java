package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaInicial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_base_de_telas_e_login.domain.UseCase.Produto.ProdutoUseCase;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.util.List;

@RestController
@RequestMapping("/productsPublico")
public class TelaInicial {


    @Autowired
    private ProdutoUseCase usercaseProduto;

    @GetMapping("/testeDeApiAberta")
    public String teste() {
        return "esse Get está aberto ao público";
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok( usercaseProduto.findAll());
    }
}
