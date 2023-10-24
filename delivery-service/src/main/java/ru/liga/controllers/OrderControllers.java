package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestOrderWithStatus;
import ru.liga.dto.response.ResponseOrder;
import ru.liga.dto.response.ResponseOrderWithStatus;
import ru.liga.service.interfaces.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Slf4j
public class OrderControllers {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderByIdBatis(@PathVariable(name = "id") Long id) {
        ResponseOrder order = orderService.getOrderByIdBatis(id);
        return (order != null)
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/hiber/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable(name = "id") Long id) {
        ResponseOrder order = orderService.getOrderById(id);
        return (order != null)
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateOrderPhone(@RequestBody RequestOrderWithStatus requestOrder) {
        ResponseOrderWithStatus responseOrder = orderService.updateOrderStatus(requestOrder);
        return (responseOrder != null)
                ? new ResponseEntity<>(responseOrder, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
