package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestRestaurant;
import ru.liga.dto.request.RequestRestaurantStatus;
import ru.liga.dto.response.ResponseRestaurantProfile;
import ru.liga.service.interfaces.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Restaurant-service используется для работы с ресторанами")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Operation(summary = "Получить информация о ресторане по ID")
   @GetMapping("/{id}")
    public ResponseEntity<ResponseRestaurantProfile> getRestaurantById(
			@PathVariable(name = "id") long id) {
        log.info("[RestaurantController:getRestaurantById]: " +
                "Попытка получить информацию о ресторане по id {}", id);
        ResponseRestaurantProfile respRestaurant = restaurantService
				.getRestaurantById(id);
        return new ResponseEntity<>(respRestaurant, HttpStatus.OK);
    }

    @Operation(summary = "Добавить новый ресторан")
    @PostMapping
    public ResponseEntity<ResponseRestaurantProfile> createNewRestaurant(
			@RequestBody RequestRestaurant requestRestaurant) {
        log.info("[RestaurantController:createNewRestaurant]: " +
                "Попытка зарегистрировать новый ресторан с данными {}"
                , requestRestaurant.toString());
        ResponseRestaurantProfile respRestaurant = restaurantService
				.createNewRestaurant(requestRestaurant);
        return new ResponseEntity<>(respRestaurant, HttpStatus.CREATED);
    }

    @Operation(summary = "Обновить статуса ресторана")
	@PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurantStatus(
			@RequestBody RequestRestaurantStatus requestRestaurantStatus,
			@PathVariable(name = "id") Long id) {
        log.info("[RestaurantController:updateRestaurantStatus]: " +
                "Попытка обновить статус ресторана c id {} на {}"
                , id, requestRestaurantStatus.getStatus().toString());
        restaurantService.updateRestaurantStatus(requestRestaurantStatus, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Изменить информацию о ресторане")
    @PutMapping
    public ResponseEntity<ResponseRestaurantProfile> editRestaurant(
			@RequestBody RequestRestaurant requestRestaurant,
			@RequestParam ("id") long id) {
        log.info("[RestaurantController:editRestaurant]: " +
                "Попытка изменить информацию о ресторане с id {}", id);
        ResponseRestaurantProfile respRestaurant = restaurantService
				.editRestaurant(requestRestaurant, id);
        return new ResponseEntity<>(respRestaurant, HttpStatus.OK);
    }
	
}