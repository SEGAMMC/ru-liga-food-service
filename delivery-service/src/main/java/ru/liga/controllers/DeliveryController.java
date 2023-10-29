package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.ResponseDeliveryOrder;
import ru.liga.service.interfaces.DeliveryService;

import java.util.List;


@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("/deliveries")
	public ResponseEntity<?> getDeliveryOrdersByStatus(@RequestParam(name = "status") RequestOrderStatus status) {
		List<ResponseDeliveryOrder> deliveryOrderList= deliveryService.getDeliveryOrdersByStatus(status);
        return (deliveryOrderList != null)
                ? new ResponseEntity<>(deliveryOrderList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //TODO
//    @GetMapping("/delivery/${id}")
//    public ResponseEntity<?> getDeliveryOrderById(@PathVariable long id) {
//		ResponseDeliveryOrder deliveryOrder = deliveryService.getDeliveryOrderById(id);
//        return (deliveryOrder != null)
//                ? new ResponseEntity<>(deliveryOrder, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping("/delivery/{id}")
    public ResponseEntity<?> updateDeliveryOrderStatus(@RequestBody RequestOrderStatus requestDeliveryStatus,
	@PathVariable(name = "id") Long id) {
        deliveryService.updateDeliveryOrderStatus(requestDeliveryStatus, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
