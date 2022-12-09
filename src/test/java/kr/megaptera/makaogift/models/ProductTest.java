package kr.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void creation() {
        Product product = new Product(1L, "이건 제목", 10_000L, "제조사", "이건 설명",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg");

        assertThat(product.getTitle()).isEqualTo("이건 제목");
        assertThat(product.getDescription()).isEqualTo("이건 설명");
    }
}
