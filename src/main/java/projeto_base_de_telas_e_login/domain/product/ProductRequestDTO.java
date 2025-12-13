package projeto_base_de_telas_e_login.domain.product;

public record ProductRequestDTO(String name, Double price) {

    public ProductRequestDTO(Product product) {
        this(product.getName(), product.getPrice());
    }
}
