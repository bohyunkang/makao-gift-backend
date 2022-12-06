package kr.megaptera.makaogift.exceptions;

public class NotEnoughAmount extends RuntimeException {
    public NotEnoughAmount() {
        super("Amount is not enough");
    }
}
