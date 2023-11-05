package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/delivery")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Delivery-service используется для распределения заказов между курьерами")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @Operation(summary = "Получить список заказов имеющие соответствующий статус")
    @GetMapping("/orders")
	public ResponseEntity<List<ResponseDeliveryOrder>> getDeliveryOrdersByStatus(
			@RequestParam(name = "status") String status) {
		List<ResponseDeliveryOrder> deliveryOrderList= deliveryService
				.getOrdersByStatusDelivery(status);
        return new ResponseEntity<>(deliveryOrderList, HttpStatus.OK);
    }

    //TODO
    @Operation(summary = "Получить статус заказа по ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDeliveryOrder> getDeliveryOrderById(
			@PathVariable long id) {
		ResponseDeliveryOrder deliveryOrder = deliveryService
				.getOrderByIdDelivery(id);
        return new ResponseEntity<>(deliveryOrder, HttpStatus.OK);
    }
    @Operation(summary = "Обновить статус заказа доставки")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDeliveryOrderStatus(
			@RequestBody RequestOrderStatus requestDeliveryStatus,
			@PathVariable(name = "id") Long id) {
        deliveryService.updateDeliveryOrderStatus(requestDeliveryStatus, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
