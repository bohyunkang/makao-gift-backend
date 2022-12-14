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

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getPrice() {
        return price;
    }

    public String getMaker() {
        return maker;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public static Product fake(Long id) {
        return new Product(id, "이건 제목", 10_000L, "제조사", "이건 설명",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg");
    }
}

