package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.response.ResponseCourier;
import ru.liga.dto.response.ResponseRestaurant;
import ru.liga.service.interfaces.CourierService;
import ru.liga.service.interfaces.RestaurantService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
@Slf4j
public class RestaurantControllers {
    private final RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable(name = "id") Long id) {
        ResponseRestaurant responseRestaurant = restaurantService.getRestaurantById(id);
        return (responseRestaurant != null)
                ? new ResponseEntity<>(responseRestaurant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<?> getCourierByPhone(@PathVariable(name = "id") Long id) {
        ResponseRestaurant responseRestaurant = restaurantService.getNameRestaurantById(id);
        return (responseRestaurant != null)
                ? new ResponseEntity<>(responseRestaurant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
