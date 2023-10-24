package ru.liga.batisMapper.restaurant;

import ru.liga.entity.Restaurant;

public interface RestaurantMapper {
    Restaurant getRestaurantById(Long id);

    String getNameRestaurantById(Long id);

}
