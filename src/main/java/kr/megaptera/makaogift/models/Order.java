package kr.megaptera.makaogift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.megaptera.makaogift.dtos.OrderCreationDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private Long productId;

    private Integer quantity;

    private Long totalPrice;

    private String receiver;

    private String address;

    private String message;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order() {
    }

    public Order(Long id, String username, Long productId, Integer quantity, Long totalPrice,
                 String receiver, String address, String message) {
        this.id = id;
        this.username = username;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
    }

    public static Order fake() {
        return new Order(1L, "boni1234", 1L, 1, 10_000L, "전제나",
                "서울시 사랑구 행복동 888번지 7층", "제나야! Merry Christmas!");
    }

    public OrderCreationDto toCreationDto() {
        return new OrderCreationDto(id, productId, quantity);
    }
}
