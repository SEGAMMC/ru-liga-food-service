package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.*;
import ru.liga.dto.response.*;
import ru.liga.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/{id}")
    public ResponseEntity<?> updateOrderStatus(
            @RequestBody RequestOrderStatus requestOrderStatus, @PathVariable(name = "id") Long id) {
        orderService.updateOrderStatus(requestOrderStatus, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() {
        ResponseOrdersList ordersList = orderService.getOrders();
        return (ordersList != null)
                ? new ResponseEntity<>(ordersList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
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
//    @PutMapping("/update")
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

//	@GetMapping("/orders")
//    List<ResponseOrder> getOrdersByStatus(@RequestParam(name = "status") RequestOrderStatus status){
//		return null;
//	}

//	@PostMapping("/order/{id}")
//    void updateOrderStatus(@RequestBody RequestOrderStatus requestOrderStatus
//            , 	@PathVariable(name = "id") long id){
//
//	}


}