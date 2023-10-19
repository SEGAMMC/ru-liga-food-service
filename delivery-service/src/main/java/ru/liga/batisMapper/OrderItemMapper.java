package ru.liga.batisMapper;

import ru.liga.entity.OrderItem;
import ru.liga.entity.RestaurantMenuItem;

public interface OrderItemMapper {
    OrderItem getOrderItemById(Long id);

    RestaurantMenuItem getRestaurantMenuItemById(Long id);

}
