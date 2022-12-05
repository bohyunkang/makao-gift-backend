package kr.megaptera.makaogift.exceptions;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(Long id) {
        super("Product not found! Product id:  " + id);
    }
}
