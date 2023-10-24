package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestNewOrderItem;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.dto.response.ResponseOrderItem;
import ru.liga.service.interfaces.OrderItemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("orderitem")
@Slf4j
public class OrderItemControllers {
    private final OrderItemService orderItemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable(name = "id") Long id) {
        ResponseOrderItem responseOrderItem = orderItemService.getOrderItemById(id);
        return (responseOrderItem != null)
                ? new ResponseEntity<>(responseOrderItem, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<?> getRestaurantMenuById(@PathVariable(name = "id") Long id) {
        ResponseMenuItem responseMenuItem = orderItemService.getMenuItem(id);
        return (responseMenuItem != null)
                ? new ResponseEntity<>(responseMenuItem, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<?> createNewItems(@RequestBody RequestNewOrderItem requestNewOrderItem) {
        ResponseOrderItem responseOrderItem = orderItemService.createNewOrderItem(requestNewOrderItem);
        return (responseOrderItem != null)
                ? new ResponseEntity<>(responseOrderItem, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
