package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestRestaurant;
import ru.liga.dto.request.RequestRestaurantStatus;
import ru.liga.dto.response.ResponseRestaurant;
import ru.liga.entity.Restaurant;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.interfaces.RestaurantService;


@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
	@Override
	public ResponseRestaurant getRestaurantById(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).get();
		return convertRestaurantToResponseRestaurant(restaurant);
    }

	@Override
	public ResponseRestaurant createNewRestaurant(RequestRestaurant requestRestaurant) {
		Restaurant restaurant = new Restaurant ();
		restaurant.setName(requestRestaurant.getName());
		restaurant.setAddress(requestRestaurant.getAddress());
		restaurant.setStatus(requestRestaurant.getStatus());
		restaurantRepository.save(restaurant);
		return convertRestaurantToResponseRestaurant(restaurant);
    }

	@Override
	public void updateRestaurantStatus(RequestRestaurantStatus requestRestaurantStatus, long id) {
		Restaurant restaurant = restaurantRepository.findById(id).get();
		restaurant.setStatus(requestRestaurantStatus.getStatus());
		restaurantRepository.save(restaurant);
	}

	@Override
	public ResponseRestaurant editRestaurant(RequestRestaurant requestRestaurant, long id) {
		Restaurant restaurant = restaurantRepository.findById(id).get();
		restaurant.setName(requestRestaurant.getName());
		restaurant.setAddress(requestRestaurant.getAddress());
		restaurant.setStatus(requestRestaurant.getStatus());		
		restaurantRepository.save(restaurant);
		return convertRestaurantToResponseRestaurant(restaurant);
    }
		
	private static ResponseRestaurant convertRestaurantToResponseRestaurant(Restaurant restaurant){
		ResponseRestaurant respRestaurant = new ResponseRestaurant ();
		respRestaurant.setId(restaurant.getId());
		respRestaurant.setName(restaurant.getName());
		respRestaurant.setAddress(restaurant.getAddress());
		respRestaurant.setStatus(restaurant.getStatus());
		return respRestaurant;
	}

}
