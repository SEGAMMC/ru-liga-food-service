package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.response.ResponseCourier;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.service.interfaces.RestaurantMenuItemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menuitem")
@Slf4j
public class MenuItemControllers {
    private final RestaurantMenuItemService restaurantMenuItemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuItemById(@PathVariable(name = "id") Long id) {
        ResponseMenuItem responseMenuItem = restaurantMenuItemService.getRestaurantItemById(id);
        return (responseMenuItem != null)
                ? new ResponseEntity<>(responseMenuItem, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
