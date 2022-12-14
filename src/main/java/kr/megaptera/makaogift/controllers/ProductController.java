package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.PagesDto;
import kr.megaptera.makaogift.dtos.ProductDto;
import kr.megaptera.makaogift.dtos.ProductsDto;
import kr.megaptera.makaogift.exceptions.ProductNotFound;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductsDto products(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer size
    ) {
        Page<Product> products = productService.products(page, size);

        List<ProductDto> productDtos = products.stream()
                .map(product -> product.toDto())
                .collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(products.getTotalPages());

        return new ProductsDto(productDtos, pagesDto);
    }

    @GetMapping("/{id}")
    public ProductDto product(
            @PathVariable("id") Long id
    ) {
        Product product = productService.product(id);

        ProductDto productDto = product.toDto();

        return productDto;
    }

    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFound() {
        return "Product Not Found!";
    }
}
