package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestMenuItem;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.dto.response.ResponseOrder;
import ru.liga.enums.OrderStatus;
import ru.liga.service.interfaces.KitchenService;

import java.util.List;

@RestController
@RequestMapping("/kitchen")
@RequiredArgsConstructor
@Slf4j
public class KitchenController {
    private final KitchenService kitchenService;

//    @GetMapping("/${id}")
//    public ResponseEntity<?> getMenuItemById(@PathVariable(name = "id") Long id) {
//
//        ResponseMenuItem respMenuItem = kitchenService.getMenuItemById(id);
//        return (respMenuItem != null)
//                ? new ResponseEntity<>(respMenuItem, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping
    public ResponseEntity<?> createNewMenuItem(@RequestBody RequestMenuItem requestMenuItem) {
		ResponseMenuItem respMenuItem = kitchenService.createNewMenuItem(requestMenuItem);
        return (respMenuItem != null)
                ? new ResponseEntity<>(respMenuItem, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
	@PutMapping
    public ResponseEntity<?> editMenuItem(@RequestBody RequestMenuItem requestMenuItem,
                                          @RequestParam ("id") long id) {
        ResponseMenuItem respMenuItem = kitchenService.editMenuItem(requestMenuItem, id);
        return (respMenuItem != null)
                ? new ResponseEntity<>(respMenuItem, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
//	@DeleteMapping("/${id}")
//    public ResponseEntity<?> deleteMenuItem(@PathVariable(name = "id") long id) {
//		kitchenService.deleteMenuItem(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//	@PostMapping("/order/{id}")
//    public ResponseEntity<?> updateOrderStatusByKitchen(
//            @RequestBody RequestOrderStatus requestOrderStatus, @PathVariable(name = "id") Long id) {
//        kitchenService.updateOrderStatusByKitchen(requestOrderStatus, id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//	@GetMapping("/orders")
//	public ResponseEntity<?> getKitchenOrdersByStatus(@RequestParam(name = "status") RequestOrderStatus status) {
//		List<ResponseOrder> kitchenOrderList = kitchenService.getOrdersByStatus(status);
//        return (kitchenOrderList != null)
//                ? new ResponseEntity<>(kitchenOrderList, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}