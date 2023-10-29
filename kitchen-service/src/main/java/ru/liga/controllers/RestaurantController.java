package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestMenuItem;
import ru.liga.dto.request.RequestRestaurant;
import ru.liga.dto.request.RequestRestaurantStatus;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.dto.response.ResponseRestaurant;
import ru.liga.service.interfaces.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController {
    private final RestaurantService restaurantService;

//   @GetMapping("/${id}")
//    public ResponseEntity<?> getRestaurantById(@PathVariable(name = "id") Long id) {
//        ResponseRestaurant respRestaurant = restaurantService.getRestaurantById(id);
//        return (respRestaurant != null)
//                ? new ResponseEntity<>(respRestaurant, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping
    public ResponseEntity<?> createNewRestaurant(@RequestBody RequestRestaurant requestRestaurant) {
        ResponseRestaurant respRestaurant = restaurantService.createNewRestaurant(requestRestaurant);
        return (respRestaurant != null)
                ? new ResponseEntity<>(respRestaurant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
	@PostMapping("/{id}")
    public ResponseEntity<?> updateRestaurantStatus(@RequestBody RequestRestaurantStatus requestRestaurantStatus, @PathVariable(name = "id") Long id) {
        restaurantService.updateRestaurantStatus(requestRestaurantStatus, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editRestaurant(@RequestBody RequestRestaurant requestRestaurant,
                                          @RequestParam ("id") long id) {
        ResponseRestaurant respRestaurant = restaurantService.editRestaurant(requestRestaurant, id);
        return (respRestaurant != null)
                ? new ResponseEntity<>(respRestaurant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
}