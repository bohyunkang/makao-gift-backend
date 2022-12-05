package kr.megaptera.makaogift.exceptions;

public class PasswordNotMatch extends RuntimeException {
    public PasswordNotMatch() {
        super("Password not match!");
    }
}
