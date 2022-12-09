package kr.megaptera.makaogift.dtos;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> products;

    private PagesDto pages;

    public ProductsDto() {
    }

    public ProductsDto(List<ProductDto> products, PagesDto pagesDto) {
        this.products = products;
        this.pages = pagesDto;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public PagesDto getPages() {
        return pages;
    }
}
