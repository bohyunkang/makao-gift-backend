package kr.megaptera.makaogift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.megaptera.makaogift.dtos.UserCreationDto;
import kr.megaptera.makaogift.dtos.UserDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "PERSON")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String encodedPassword;

    private String name;

    private Long amount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public User(Long id, String username, String name, Long amount) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }

    public String username() {
        return username;
    }

    public String name() {
        return name;
    }

    public Long amount() {
        return amount;
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.encodedPassword = passwordEncoder.encode(password);
    }

    public UserDto toDto() {
        return new UserDto(username, name, amount);
    }

    public UserCreationDto toCreationDto() {
        return new UserCreationDto(id, username, name);
    }

    public void setInitialAmount() {
        Long initialAmount = 50_000L;

        this.amount = initialAmount;
    }

    public static User fake(String username) {
        return new User(1L, username, "강보니", 50_000L);
    }
}
