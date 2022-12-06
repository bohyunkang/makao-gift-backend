package kr.megaptera.makaogift.controllers;

import jakarta.validation.Valid;
import kr.megaptera.makaogift.dtos.OrderRequestDto;
import kr.megaptera.makaogift.dtos.OrderCreationDto;
import kr.megaptera.makaogift.exceptions.OrderFailed;
import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // TODO: 입력값 예외처리 기능 추가 구현 필요!
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
}
