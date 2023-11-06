package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestRestaurant;
import ru.liga.dto.request.RequestRestaurantStatus;
import ru.liga.dto.response.ResponseRestaurantProfile;
import ru.liga.entity.Restaurant;
import ru.liga.exception.RestaurantNotFoundException;
import ru.liga.repository.hibernate.RestaurantRepository;
import ru.liga.service.interfaces.RestaurantService;
import ru.liga.util.Validator;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final Validator validator;

    @Override
    public ResponseRestaurantProfile getRestaurantById(long id) {
        validator.isPositive(id);
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        log.info("[RestaurantServiceImpl:getRestaurantById]: " +
                "Получили информацию о ресторане с id {}", id);
        return mapRestaurantToResponseRestaurant(restaurant);
    }

    @Override
    public ResponseRestaurantProfile createNewRestaurant(
            RequestRestaurant requestRestaurant) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(requestRestaurant.getName());
        restaurant.setAddress(requestRestaurant.getAddress());
        restaurant.setStatus(requestRestaurant.getStatus());
        restaurant = restaurantRepository.save(restaurant);
        log.info("[RestaurantServiceImpl:createNewRestaurant]: " +
                "Зарегистрировали новый ресторан с id {}", restaurant.getId());
        return mapRestaurantToResponseRestaurant(restaurant);
    }

    @Override
    public void updateRestaurantStatus(RequestRestaurantStatus requestRestaurantStatus
            , long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant.setStatus(requestRestaurantStatus.getStatus());
        restaurant = restaurantRepository.save(restaurant);
        log.info("[RestaurantServiceImpl:updateRestaurantStatus]: " +
                        "Изменили статус ресторана с id {} на новый статус - {}"
                , id, restaurant.getStatus());
    }

    @Override
    public ResponseRestaurantProfile editRestaurant(
            RequestRestaurant requestRestaurant, long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant.setName(requestRestaurant.getName());
        restaurant.setAddress(requestRestaurant.getAddress());
        restaurant.setStatus(requestRestaurant.getStatus());
        restaurant = restaurantRepository.save(restaurant);
        log.info("[RestaurantServiceImpl:editRestaurant]: " +
                "Изменили информацию о ресторане с id {}", id);
        return mapRestaurantToResponseRestaurant(restaurant);
    }

    private static ResponseRestaurantProfile mapRestaurantToResponseRestaurant(
            Restaurant restaurant) {
        ResponseRestaurantProfile respRestaurant =
                new ResponseRestaurantProfile();
        respRestaurant.setId(restaurant.getId());
        respRestaurant.setName(restaurant.getName());
        respRestaurant.setAddress(restaurant.getAddress());
        respRestaurant.setStatus(restaurant.getStatus());
        return respRestaurant;
    }

}