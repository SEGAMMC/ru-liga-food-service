package ru.liga.repository;

import org.springframework.data.repository.CrudRepository;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;

public interface OrderItemRepository
        extends CrudRepository<OrderItem, Long> {

}
