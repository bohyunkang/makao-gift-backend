package kr.megaptera.makaogift.controllers;

import jakarta.validation.Valid;
import kr.megaptera.makaogift.dtos.OrderDto;
import kr.megaptera.makaogift.dtos.OrderRequestDto;
import kr.megaptera.makaogift.dtos.OrderCreationDto;
import kr.megaptera.makaogift.dtos.OrdersDto;
import kr.megaptera.makaogift.exceptions.InvalidUser;
import kr.megaptera.makaogift.exceptions.OrderFailed;
import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OrdersDto orders(
            @RequestAttribute("username") String username,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "8") Integer size
    ) {
        return orderService.orders(username, page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto detail(
            @PathVariable("id") Long id,
            @RequestAttribute("username") String username
    ) {
        return orderService.detail(id, username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCreationDto order(
            @RequestAttribute("username") String username,
            @Valid @RequestBody OrderRequestDto orderRequestDto
    ) {
        Order order = orderService.order(
                username,
                orderRequestDto.getProductId(),
                orderRequestDto.getQuantity(),
                orderRequestDto.getReceiver(),
                orderRequestDto.getAddress(),
                orderRequestDto.getMessage()
        );

        return order.toCreationDto();
    }

    @ExceptionHandler(OrderFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String orderFailed() {
        return "Order failed!";
    }

    @ExceptionHandler(InvalidUser.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidUser() {
        return "Invalid user!";
    }
}
