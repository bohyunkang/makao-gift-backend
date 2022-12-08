package kr.megaptera.makaogift.exceptions;

public class RegisterFailed extends RuntimeException {
    public RegisterFailed() {
        super("Register failed");
    }
}
