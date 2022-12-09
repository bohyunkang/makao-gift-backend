package kr.megaptera.makaogift.dtos;

import java.util.List;

public class OrdersDto {
    private List<OrderDto> orders;

    private PagesDto pages;

    public OrdersDto() {
    }

    public OrdersDto(List<OrderDto> orders, PagesDto pages) {
        this.orders = orders;
        this.pages = pages;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public PagesDto getPages() {
        return pages;
    }
}
