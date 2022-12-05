package kr.megaptera.makaogift.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String username) {
        super("User not found " + username.toString());
    }
}
