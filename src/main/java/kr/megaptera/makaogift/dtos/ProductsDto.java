package kr.megaptera.makaogift.dtos;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> product;

    public ProductsDto(List<ProductDto> product) {
        this.product = product;
    }

    public List<ProductDto> getProduct() {
        return product;
    }
}
