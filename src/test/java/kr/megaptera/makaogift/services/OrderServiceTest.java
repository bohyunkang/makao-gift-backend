package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.dtos.OrderDto;
import kr.megaptera.makaogift.dtos.OrdersDto;
import kr.megaptera.makaogift.exceptions.InvalidUser;
import kr.megaptera.makaogift.exceptions.OrderFailed;
import kr.megaptera.makaogift.exceptions.OrderNotFound;
import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.OrderRepository;
import kr.megaptera.makaogift.repositories.ProductRepository;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderServiceTest {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        productRepository = mock(ProductRepository.class);
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(userRepository, productRepository, orderRepository);
    }

    @Test
    void orders() {
        User user = User.fake("boni1234");

        given(productRepository.findById(any()))
                .willReturn(Optional.of(Product.fake(1L)));

        given(orderRepository.findAllByUsername(any()))
                .willReturn(List.of(Order.fake()));

        OrdersDto ordersDto = orderService.orders(user.username());

        assertThat(ordersDto).isNotNull();
        assertThat(ordersDto.getOrders().get(0).getProduct().getTitle())
                .isEqualTo("이건 제목");
    }

    @Test
    void orderDetail() {
        User user = User.fake("boni1234");

        given(productRepository.findById(any()))
                .willReturn(Optional.of(Product.fake(1L)));

        given(orderRepository.findById(any()))
                .willReturn(Optional.of(Order.fake()));

        OrderDto orderDto = orderService.detail(1L, user.username());

        assertThat(orderDto.getId()).isEqualTo(1L);
    }

    @Test
    void orderDetailWithOtherUser() {
        given(orderRepository.findById(any()))
                .willReturn(Optional.of(Order.fake()));

        assertThrows(InvalidUser.class, () -> {
            orderService.detail(1L, Order.fake().getUsername() + "xxx");
        });
    }

    @Test
    void orderDetailNotFound() {
        assertThrows(OrderNotFound.class, () -> {
            orderService.detail(1L, "boni1234");
        });
    }

    @Test
    void orderSuccess() {
        User user = User.fake("boni1234");
        Long initialAmount = user.amount();

        given(userRepository.findByUsername(any()))
                .willReturn(Optional.of(user));

        Product product = Product.fake(1L);

        given(productRepository.findById(any()))
                .willReturn(Optional.of(product));

        String username = "boni1234";
        Long productId = 1L;
        Integer quantity = 1;
        String receiver = "전제나";
        String address = "서울시 사랑구 행복동 888번지 7층";
        String message = "제나야! Merry Christmas!";

        Order order = orderService.order(username, productId, quantity, receiver, address, message);

        assertThat(order).isNotNull();
        assertThat(user.amount()).isEqualTo(initialAmount - product.getPrice() * quantity);

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void orderWithoutUser() {
        Product product = Product.fake(1L);

        given(productRepository.findById(any()))
                .willReturn(Optional.of(product));

        String username = "boni1234";
        Long productId = 1L;
        Integer quantity = 1;
        String receiver = "전제나";
        String address = "서울시 사랑구 행복동 888번지 7층";
        String message = "제나야! Merry Christmas!";

        assertThrows(OrderFailed.class, () -> {
            orderService.order(username, productId, quantity, receiver, address, message);
        });
    }

    @Test
    void orderWithoutProduct() {
        given(userRepository.findByUsername(any()))
                .willReturn(Optional.of(User.fake("boni1234")));

        String username = "boni1234";
        Long productId = 1L;
        Integer quantity = 1;
        String receiver = "전제나";
        String address = "서울시 사랑구 행복동 888번지 7층";
        String message = "제나야! Merry Christmas!";

        assertThrows(OrderFailed.class, () -> {
            orderService.order(username, productId, quantity, receiver, address, message);
        });
    }
}
