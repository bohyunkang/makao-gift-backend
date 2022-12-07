package kr.megaptera.makaogift.dtos;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public class OrderDto {
    private Long id;

    private Integer quantity;

    private Long totalPrice;

    private String receiver;

    private String address;

    private String message;

    private ProductDto product;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public OrderDto() {
    }

    public OrderDto(Long id, Integer quantity, Long totalPrice, String receiver,
                    String address, String message, ProductDto productDto,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
        this.product = productDto;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
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

    public ProductDto getProduct() {
        return product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
