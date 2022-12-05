package kr.megaptera.makaogift.dtos;

public class UserDto {
    private String username;

    private String name;

    private Long amount;

    public UserDto() {
    }

    public UserDto(String username, String name, Long amount) {
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
}
