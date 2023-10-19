package ru.liga.batisMapper;

import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.enums.OrderStatus;

import java.util.List;

public interface OrderMapper {
    Order getOrderById(Long id);

    List<OrderItem> getOrderItemById(Long id);

    List<Order> getOrderByStatus(OrderStatus status);

    void updateOrderStatus(Order order);
}
