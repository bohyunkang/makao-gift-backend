package kr.megaptera.makaogift.dtos;

public class OrderCreationDto {
    private Long id;

    private Long productId;

    private Integer quantity;

    public OrderCreationDto() {
    }

    public OrderCreationDto(Long id, Long productId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
