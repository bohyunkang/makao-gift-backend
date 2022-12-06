package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.OrderFailed;
import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.OrderRepository;
import kr.megaptera.makaogift.repositories.ProductRepository;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Order order(String username, Long productId, Integer quantity,
                       String receiver, String address, String message) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new OrderFailed());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new OrderFailed());

        user.order(product, quantity);

        Long totalPrice = product.getPrice() * quantity;

        Order order = new Order(null, username, productId,
                quantity, totalPrice, receiver, address, message);

        orderRepository.save(order);

        return order;
    }
}
