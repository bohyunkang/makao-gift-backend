package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.dtos.OrderDto;
import kr.megaptera.makaogift.dtos.OrdersDto;
import kr.megaptera.makaogift.exceptions.AuthenticationError;
import kr.megaptera.makaogift.exceptions.InvalidUser;
import kr.megaptera.makaogift.exceptions.OrderFailed;
import kr.megaptera.makaogift.exceptions.OrderNotFound;
import kr.megaptera.makaogift.exceptions.ProductNotFound;
import kr.megaptera.makaogift.exceptions.RegisterFailed;
import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.OrderRepository;
import kr.megaptera.makaogift.repositories.ProductRepository;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public OrderService(UserRepository userRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrdersDto orders(String username) {
        List<Order> orders = orderRepository.findAllByUsername(username);

        List<OrderDto> orderDtos = orders.stream()
                .map(order -> {
                    Product product = productRepository.findById(order.getProductId())
                            .orElseThrow(() -> new ProductNotFound(order.getProductId()));

                    return new OrderDto(order.getId(), order.getQuantity(), order.getTotalPrice(),
                            order.getReceiver(), order.getAddress(), order.getMessage(),
                            product.toDto(), order.getCreatedAt(), order.getUpdatedAt());
                })
                .collect(Collectors.toList());

        return new OrdersDto(orderDtos);
    }

    public OrderDto detail(Long id, String username) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound(id));

        if (!Objects.equals(order.getUsername(), username)) {
            throw new InvalidUser();
        }

        Product product = productRepository.findById(order.getProductId())
                .orElseThrow(() -> new ProductNotFound(order.getProductId()));

        return new OrderDto(order.getId(), order.getQuantity(), order.getTotalPrice(),
                order.getReceiver(), order.getAddress(), order.getMessage(),
                product.toDto(), order.getCreatedAt(), order.getUpdatedAt());
    }

    public Order order(String username, Long productId, Integer quantity,
                       String receiver, String address, String message) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new OrderFailed());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new OrderFailed());


        if (receiver.isBlank() || address.isBlank()) {
            throw new OrderFailed();
        }

        if (!Pattern.matches("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{3,7}$", receiver)) {
            throw new OrderFailed();
        }

        user.order(product, quantity);

        Long totalPrice = product.getPrice() * quantity;

        Order order = new Order(null, username, productId,
                quantity, totalPrice, receiver, address, message);

        orderRepository.save(order);

        return order;
    }
}
