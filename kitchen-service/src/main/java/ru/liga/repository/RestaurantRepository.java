package ru.liga.repository;

import org.springframework.data.repository.CrudRepository;
import ru.liga.entity.Restaurant;

public interface RestaurantRepository
        extends CrudRepository<Restaurant, Long> {

}
