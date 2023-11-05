package ru.liga.repository.hibernate;

import org.springframework.data.repository.CrudRepository;
import ru.liga.entity.OrderItem;

public interface OrderItemRepository
        extends CrudRepository<OrderItem, Long> {

}
