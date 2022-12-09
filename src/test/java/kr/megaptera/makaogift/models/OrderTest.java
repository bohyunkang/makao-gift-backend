package kr.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void creation() {
        Order order = new Order(1L, "boni1234", 1L, 1, 10_000L,
                "전제나", "서울시 사랑구 행복동 888번지 7층", "제나야! Merry Christmas!");

        assertThat(order).isNotNull();
        assertThat(order.getReceiver()).isEqualTo("전제나");
        assertThat(order.getTotalPrice()).isEqualTo(10_000L);
        assertThat(order.getAddress()).isEqualTo("서울시 사랑구 행복동 888번지 7층");
    }
}
