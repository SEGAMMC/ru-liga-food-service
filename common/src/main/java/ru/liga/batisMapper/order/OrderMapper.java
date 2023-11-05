package ru.liga.batisMapper.order;

import ru.liga.entity.Order;

public interface OrderMapper {
    Order getOrderById(Long id);

    void updateOrderStatus(Order order);
}
