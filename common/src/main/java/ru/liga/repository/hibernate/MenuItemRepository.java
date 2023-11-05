package ru.liga.repository.hibernate;

import org.springframework.data.repository.CrudRepository;
import ru.liga.entity.RestaurantMenuItem;

public interface MenuItemRepository
        extends CrudRepository<RestaurantMenuItem, Long> {

}
