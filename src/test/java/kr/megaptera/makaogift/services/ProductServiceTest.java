package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.ProductNotFound;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ProductServiceTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void products() {
        ProductRepository productRepository = mock(ProductRepository.class);

        given(productRepository.findAll())
                .willReturn(List.of(new Product(1L, "상품1", 10_000L, "제조사", "이 상품은 첫 번째 상품입니다",
                        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg")));

        ProductService productService = new ProductService(productRepository);

        assertThat(productService.products()).hasSize(1);
    }

    @Test
    void product() {
        ProductRepository productRepository = mock(ProductRepository.class);

        given(productRepository.findById(1L))
                .willReturn(Optional.of(new Product(1L, "상품1", 10_000L, "제조사",
                        "이 상품은 첫 번째 상품입니다", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg")));

        ProductService productService = new ProductService(productRepository);

        Product product = productService.product(1L);

        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getTitle()).isEqualTo("상품1");
        assertThat(product.getPrice()).isEqualTo(10_000L);
    }

    @Test
    void productNotFound() {
        ProductRepository productRepository = mock(ProductRepository.class);

        ProductService productService = new ProductService(productRepository);

        assertThrows(ProductNotFound.class, () -> {
            productService.product(1L);
        });
    }
}
