package ru.liga.service.interfaces;


import ru.liga.dto.request.RequestRestaurant;
import ru.liga.dto.request.RequestRestaurantStatus;
import ru.liga.dto.response.ResponseRestaurantProfile;

public interface RestaurantService {
	
	ResponseRestaurantProfile getRestaurantById(long id);
	
	ResponseRestaurantProfile createNewRestaurant(RequestRestaurant requestRestaurant);
	
	ResponseRestaurantProfile editRestaurant(RequestRestaurant requestRestaurant, long id);

	void updateRestaurantStatus(RequestRestaurantStatus requestRestaurantStatus, long id);
        
}
