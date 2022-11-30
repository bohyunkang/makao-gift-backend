package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.ProductDto;
import kr.megaptera.makaogift.dtos.ProductsDto;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ProductsDto products() {
        List<Product> products = productService.products();

        List<ProductDto> productDtos = products.stream()
                .map(product -> product.toDto())
                .collect(Collectors.toList());

        return new ProductsDto(productDtos);
    }
}