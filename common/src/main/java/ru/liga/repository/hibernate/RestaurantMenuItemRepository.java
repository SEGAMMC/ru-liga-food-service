package ru.liga.repository.hibernate;

import org.springframework.data.repository.CrudRepository;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;

public interface RestaurantMenuItemRepository
        extends CrudRepository<RestaurantMenuItem, Long> {
}
