package ru.liga.service.interfaces;

import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.response.ResponseCourier;
import ru.liga.dto.response.ResponseOrder;
import ru.liga.dto.response.ResponseRestaurant;

public interface RestaurantService {
    ResponseRestaurant getRestaurantById(Long id);

    ResponseRestaurant getNameRestaurantById(Long id);
}


