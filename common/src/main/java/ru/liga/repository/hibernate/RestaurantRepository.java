package ru.liga.repository.hibernate;

import org.springframework.data.repository.CrudRepository;
import ru.liga.entity.Restaurant;

public interface RestaurantRepository
        extends CrudRepository<Restaurant, Long> {

}
