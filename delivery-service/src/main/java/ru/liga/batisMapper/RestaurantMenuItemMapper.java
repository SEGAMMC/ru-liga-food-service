package ru.liga.batisMapper;

import ru.liga.entity.RestaurantMenuItem;

public interface RestaurantMenuItemMapper {

    RestaurantMenuItem getRestaurantMenuItemById(Long id);

    String getNameRestaurantMenuItemById(Long id);

    String getDescriptionRestaurantMenuItemById(Long id);

    void updateRestaurantMenuItem(RestaurantMenuItem restaurantMenuItem);

}
