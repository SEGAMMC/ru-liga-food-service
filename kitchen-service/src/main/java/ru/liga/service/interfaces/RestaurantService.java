package ru.liga.service.interfaces;


import ru.liga.dto.request.RequestRestaurant;
import ru.liga.dto.request.RequestRestaurantStatus;
import ru.liga.dto.response.ResponseRestaurant;

public interface RestaurantService {
	
	ResponseRestaurant getRestaurantById(Long id);
	
	ResponseRestaurant createNewRestaurant(RequestRestaurant requestRestaurant);
	
	ResponseRestaurant editRestaurant(RequestRestaurant requestRestaurant, long id);

	void updateRestaurantStatus(RequestRestaurantStatus requestRestaurantStatus, long id);
        
}