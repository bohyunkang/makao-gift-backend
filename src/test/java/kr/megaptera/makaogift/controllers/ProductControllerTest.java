package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.config.EnableMockMvc;
import kr.megaptera.makaogift.exceptions.ProductNotFound;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableMockMvc
@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void products() throws Exception {
        Product product = new Product(1L, "여긴 제목", 10_000L, "메이커",
                "이건 설명", "http:localhost:8000/url");

        given(productService.products()).willReturn(List.of(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("여긴 제목")
                ));

        verify(productService).products();
    }

    @Test
    void product() throws Exception {
        Product product = new Product(1L, "여긴 제목", 10_000L, "메이커",
                "이건 설명", "http:localhost:8000/url");

        given(productService.product(1L)).willReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("여긴 제목")
                ));

        verify(productService).product(1L);
    }

    @Test
    void productWithNotFound() throws Exception {
        given(productService.product(any())).willThrow(new ProductNotFound(1L));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isNotFound());
    }
}
