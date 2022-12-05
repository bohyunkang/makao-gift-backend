package kr.megaptera.makaogift.dtos;

public class UserCreationDto {
    private Long id;
    private String username;
    private String name;

    public UserCreationDto() {
    }

    public UserCreationDto(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
}
