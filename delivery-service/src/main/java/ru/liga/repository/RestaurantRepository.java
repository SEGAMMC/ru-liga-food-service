package ru.liga.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;
import ru.liga.entity.enums.OrderStatus;

public interface RestaurantRepository
        extends CrudRepository<Restaurant, Long> {

}
