package ru.liga.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.controllers.request.RequestOrder;
import ru.liga.controllers.response.ResponseOrder;
import ru.liga.controllers.response.ResponseOrderAccept;
import ru.liga.controllers.response.ResponseOrdersList;
import ru.liga.service.OrderService;


@RestController
@RequestMapping("")
public class OrderControllers {
    private final OrderService orderService;

    public OrderControllers(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() {
        ResponseOrdersList ordersList = orderService.getOrders();
        return (ordersList != null)
                ? new ResponseEntity<>(ordersList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable(name = "id") long id) {
        ResponseOrder order = orderService.getOrderById(id);
        return (order != null)
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewOrder(@RequestBody RequestOrder requestOrder) {
        ResponseOrderAccept responseOrder = orderService
                .createNewOrder(requestOrder);
        return (responseOrder != null)
                ? new ResponseEntity<>(responseOrder, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateOrder(@RequestBody RequestOrder requestOrder) {
        ResponseOrder order = orderService.updateOrder(requestOrder);
        return (order != null)
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable(name = "id") String id) {
        ResponseOrder order = orderService.deleteOrderById(id);
        return (order != null)
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
