package kr.megaptera.makaogift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.megaptera.makaogift.dtos.ProductDto;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Long price;

    private String maker;

    private String description;

    private String imageUrl;

    public Product() {
    }

    public Product(Long id, String title, Long price,
                   String maker, String description, String imageUrl) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.maker = maker;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public ProductDto toDto() {
        return new ProductDto(id, title, price, maker, description, imageUrl);
    }
}

