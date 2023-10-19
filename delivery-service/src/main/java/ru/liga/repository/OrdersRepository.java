package ru.liga.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entity.Order;
import ru.liga.entity.enums.OrderStatus;

public interface OrdersRepository
        extends CrudRepository<Order, Long> {

    Order findOrderByStatus (OrderStatus status);

    @Modifying
    @Transactional
    @Query("update Order ord set ord.status = :status where ord.id = :id")
    void updateOrderStatus(@Param("id") Long registryInfoId, @Param("status") OrderStatus status);

    @Transactional(readOnly = true)
    @Query("select ord from Order ord where ord.status = :status")
    Order findOrderByStatusQuery(@Param("status") OrderStatus status);
}
