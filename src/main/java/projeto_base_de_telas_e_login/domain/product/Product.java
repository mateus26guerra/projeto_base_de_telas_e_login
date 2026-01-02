package projeto_base_de_telas_e_login.domain.product;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Double price;
    @Column(name = "image_url")
    private String imagemUrl;

    public Product() {}

    public Product(ProductRequestDTO dto) {
        this.name = dto.name();
        this.price = dto.price();
        this.imagemUrl = dto.imagemUrl();
        this.id = dto.id();
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
