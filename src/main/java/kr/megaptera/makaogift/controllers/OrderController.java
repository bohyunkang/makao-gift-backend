package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public OrderResultDto makeOrder(
//            @RequestBody OrderRequestDto orderRequestDto
//    ) {
//        Order order = orderService.makeOrder();
//    }
}
