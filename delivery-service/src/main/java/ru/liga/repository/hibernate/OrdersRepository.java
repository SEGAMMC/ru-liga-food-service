package ru.liga.repository.hibernate;

import org.springframework.data.repository.CrudRepository;
import ru.liga.entity.Order;
import ru.liga.enums.OrderStatus;

public interface OrdersRepository
        extends CrudRepository<Order, Long> {

    Order getOrderByStatus(OrderStatus status);

}
