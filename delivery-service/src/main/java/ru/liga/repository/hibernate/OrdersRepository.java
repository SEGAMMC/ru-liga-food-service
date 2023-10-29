package ru.liga.repository.hibernate;

import org.springframework.data.repository.CrudRepository;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.entity.Order;

import java.util.List;

public interface OrdersRepository
        extends CrudRepository<Order, Long> {

    List<Order> getOrderByStatus(RequestOrderStatus status);

}
