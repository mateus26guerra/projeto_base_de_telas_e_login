package projeto_base_de_telas_e_login.domain.product;

public record ProductRequestDTO(int id,String name, Double price, String imagemUrl) {

    public ProductRequestDTO(Product product) {
        this(product.getId(),product.getName(), product.getPrice(),product.getImagemUrl());
    }
}
