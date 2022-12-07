package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.config.EnableMockMvc;
import kr.megaptera.makaogift.dtos.OrderDto;
import kr.megaptera.makaogift.dtos.OrdersDto;
import kr.megaptera.makaogift.dtos.ProductDto;
import kr.megaptera.makaogift.exceptions.OrderFailed;
import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.services.OrderService;
import kr.megaptera.makaogift.services.ProductService;
import kr.megaptera.makaogift.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableMockMvc
@WebMvcTest(OrderController.class)
@ActiveProfiles("test")
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderService orderService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    private ProductDto productDto;

    private OrderDto orderDto;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode("boni1234");

        productDto = new ProductDto(1L, "상품1", 10_000L, "제조사1",
                "이 상품1은 이러이러하답니다", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg");

        orderDto = new OrderDto(1L, 1, 10_000L, "전제나",
                "서울시 사랑구 행복동", "메리 크리스마스!", productDto, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void orders() throws Exception {
        given(orderService.orders("boni1234"))
                .willReturn(new OrdersDto(List.of(orderDto)));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"orders\":[")
                ));
    }

    @Test
    void ordersWithWrongToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .header("Authorization", "Bearer xxx"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderDetail() throws Exception {
        given(orderService.detail(any(), any()))
                .willReturn(orderDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"product\":{")
                ));
    }


    @Test
    void orderDetailWithWrongToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/1")
                        .header("Authorization", "Bearer xxx"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderSuccess() throws Exception {
        given(orderService.order(any(), any(), any(), any(), any(), any()))
                .willReturn(Order.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"quantity\":1," +
                                "\"receiver\":\"전제나\"," +
                                "\"address\":\"서울시 사랑구 행복동 888번지 7층\"," +
                                "\"message\":\"제나야! Merry Christmas!\"" +
                                "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void orderWithWrongToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer xxx")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"quantity\":1," +
                                "\"receiver\":\"전제나\"," +
                                "\"address\":\"서울시 사랑구 행복동 888번지 7층\"," +
                                "\"message\":\"제나야! Merry Christmas!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderFailed() throws Exception {
        given(orderService.order(any(), any(), any(), any(), any(), any()))
                .willThrow(new OrderFailed());

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"quantity\":1," +
                                "\"receiver\":\"전제나\"," +
                                "\"address\":\"서울시 사랑구 행복동 888번지 7층\"," +
                                "\"message\":\"제나야! Merry Christmas!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}
