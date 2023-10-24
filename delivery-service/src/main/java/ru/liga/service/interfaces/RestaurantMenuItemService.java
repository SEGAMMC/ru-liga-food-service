package ru.liga.service.interfaces;

import ru.liga.dto.response.ResponseMenuItem;

public interface RestaurantMenuItemService {
    ResponseMenuItem getRestaurantItemById(Long id);

}


