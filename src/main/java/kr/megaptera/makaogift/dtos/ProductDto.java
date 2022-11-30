package kr.megaptera.makaogift.dtos;

public class ProductDto {
    private Long id;

    private String title;

    private Long price;

    private String maker;

    private String description;

    private String imageUrl;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, Long price,
                      String maker, String description, String imageUrl) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.maker = maker;
        this.description = description;
        this.imageUrl = imageUrl;
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
}
