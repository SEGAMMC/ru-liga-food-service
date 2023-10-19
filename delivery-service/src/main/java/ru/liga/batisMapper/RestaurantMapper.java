package ru.liga.batisMapper;


import ru.liga.entity.Restaurant;

public interface RestaurantMapper {

    Restaurant getRestaurantById(Long id);

    Restaurant getRestaurantByPhone(String phone);

    String getNameRestaurantById(Long id);

    void updateRestaurantPhone(Restaurant restaurant);

}
