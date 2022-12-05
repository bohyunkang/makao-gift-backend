package kr.megaptera.makaogift.dtos;

public class UserRegistrationDto {
    private String name;

    private String username;

    private String password;

    private String confirmPassword;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String name, String username,
                               String password, String confirmPassword) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
