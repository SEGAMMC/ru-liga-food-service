package ru.liga.repository.hibernate;

import org.springframework.data.repository.CrudRepository;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.enums.OrderStatus;

import java.util.List;

public interface OrdersRepository
        extends CrudRepository<Order, Long> {

    List<Order> getOrdersByStatus(OrderStatus status);

    Iterable<Order> findByCustomerId(Customer customerId);

    Order findOrderByUuid(String uuid);

}
