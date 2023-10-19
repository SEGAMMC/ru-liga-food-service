package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RequestOrder;
import ru.liga.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
@Slf4j
public class OrderControllers {
    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createNewOrder(@RequestBody RequestOrder requestOrder) {
        var responseOrder = orderService
                .createNewOrder(requestOrder);
        return (responseOrder != null)
                ? new ResponseEntity<>(responseOrder, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }





//TODO проверить методы взаимодействия с БД

//    @GetMapping("/orders")
//    public ResponseEntity<?> getOrders() {
//        ResponseOrdersList ordersList = orderService.getOrders();
//        return (ordersList != null)
//                ? new ResponseEntity<>(ordersList, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//
//    @GetMapping("/order/{id}")
//    public ResponseEntity<?> getOrderById(@PathVariable(name = "id") long id) {
//        ResponseOrder order = orderService.getOrderById(id);
//        return (order != null)
//                ? new ResponseEntity<>(order, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<?> createNewOrder(@RequestBody RequestOrder requestOrder) {
//        ResponseOrderAccept responseOrder = orderService
//                .createNewOrder(requestOrder);
//        return (responseOrder != null)
//                ? new ResponseEntity<>(responseOrder, HttpStatus.CREATED)
//                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
//    @PutMapping("update")
//    public ResponseEntity<?> updateOrder(@RequestBody RequestOrder requestOrder) {
//        ResponseOrder order = orderService.updateOrder(requestOrder);
//        return (order != null)
//                ? new ResponseEntity<>(order, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteOrderById(@PathVariable(name = "id") String id) {
//        ResponseOrder order = orderService.deleteOrderById(id);
//        return (order != null)
//                ? new ResponseEntity<>(order, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
