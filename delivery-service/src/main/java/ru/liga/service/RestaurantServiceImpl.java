package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.response.ResponseCourier;
import ru.liga.dto.response.ResponseRestaurant;
import ru.liga.entity.Courier;
import ru.liga.entity.Restaurant;
import ru.liga.repository.mybatis.CourierRepositoryBatis;
import ru.liga.repository.mybatis.RestaurantRepositoryBatis;
import ru.liga.service.interfaces.CourierService;
import ru.liga.service.interfaces.RestaurantService;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepositoryBatis restaurantRepositoryBatis;

    @Override
    public ResponseRestaurant getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepositoryBatis.getRestaurantById(id);
        ResponseRestaurant responseRestaurant = new ResponseRestaurant();
        responseRestaurant.setId(restaurant.getId());
        responseRestaurant.setAddress(restaurant.getAddress());
        responseRestaurant.setStatus(restaurant.getStatus());
        responseRestaurant.setName(restaurant.getName());
        return responseRestaurant;
    }

    @Override
    public ResponseRestaurant getNameRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepositoryBatis.getRestaurantById(id);
        ResponseRestaurant responseRestaurant = new ResponseRestaurant();
        responseRestaurant.setName(restaurant.getName());
        return responseRestaurant;
    }
}
