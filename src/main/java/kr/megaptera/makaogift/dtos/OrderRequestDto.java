package kr.megaptera.makaogift.dtos;

public class OrderRequestDto {
    private Long productId;

    private Integer quantity;

    private String receiver;

    private String address;

    private String message;

    public OrderRequestDto() {
    }

    public OrderRequestDto(Long productId, Integer quantity,
                           String receiver, String address, String message) {
        this.productId = productId;
        this.quantity = quantity;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }
}
