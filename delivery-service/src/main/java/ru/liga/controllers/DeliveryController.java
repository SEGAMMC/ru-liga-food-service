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
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Delivery-service используется для распределения заказов между курьерами")
public class DeliveryController {
    private final DeliveryService deliveryService;


    @Operation(summary = "Получить список доступных заказов имеющие статус DELIVERY_PENDING")
    @GetMapping("")
    public ResponseEntity<List<ResponseDeliveryOrder>> getDeliveryOrdersByStatusPending(
            @RequestParam(name = "id") long id) {
        log.info("[DeliveryController:getDeliveryOrdersByStatusPending]: " +
                "Попытка получить список доступных заказов по статусу DELIVERY_PENDING");
        List<ResponseDeliveryOrder> deliveryOrderList = deliveryService
                .getDeliveryOrdersByStatusPending(id);
        return new ResponseEntity<>(deliveryOrderList, HttpStatus.OK);
    }


    @Operation(summary = "Обновить статус заказа (DELIVERING/Принят в доставку)")
    @PutMapping("/{uuid}/take")
    public ResponseEntity<Void> updateOrderStatusByDeliveryTake(
            @PathVariable(name = "uuid") String uuid) {
        log.info("[KitchenController:updateOrderStatusByDeliveryTake]: " +
                        "Попытка обновить статус заказа id {} на новый " +
                        "статус DELIVERING/Принят в доставку", uuid);
        deliveryService.updateOrderStatusByDeliveryTake(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Обновить статус заказа (Complete/Доставка выполнена)")
    @PutMapping("/{uuid}/complete")
    public ResponseEntity<Void> updateOrderStatusByDeliveryComplete(
            @PathVariable(name = "uuid") String uuid) {
        log.info("[KitchenController:updateOrderStatusByDeliveryTake]: " +
                "Попытка обновить статус заказа id {} на новый " +
                "статус Complete/Доставка выполнена", uuid);
        deliveryService.updateOrderStatusByDeliveryComplete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }





//    @Operation(summary = "Обновить статус заказа доставки")
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateDeliveryOrderStatus(
//            @RequestBody RequestOrderStatus requestDeliveryStatus,
//            @PathVariable(name = "id") Long id) {
//        log.info("[DeliveryController:updateDeliveryOrderStatus]: " +
//                        "Попытка изменить статус доставки для заказа с id {} на статус {}"
//                , id, requestDeliveryStatus.getStatus().toString());
//        deliveryService.updateDeliveryOrderStatus(requestDeliveryStatus, id);
//        return new ResponseEntity<>(HttpStatus.OK);
//}


//    @Operation(summary = "Получить список заказов имеющие соответствующий статус")
//    @GetMapping("/orders")
//    public ResponseEntity<List<ResponseDeliveryOrder>> getDeliveryOrdersByStatus11(
//            @RequestParam(name = "status") String status) {
//        log.info("[DeliveryController:getDeliveryOrdersByStatus]: " +
//                "Попытка получить список заказов имеющие статус {}", status);
//        List<ResponseDeliveryOrder> deliveryOrderList = deliveryService
//                .getDeliveryOrdersByStatusPending();
//        return new ResponseEntity<>(deliveryOrderList, HttpStatus.OK);
//    }

//    //TODO
//    @Operation(summary = "Получить информацию о заказе по ID")
//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseDeliveryOrder> getDeliveryOrderById(
//            @PathVariable long id) {
//        log.info("[DeliveryController:getDeliveryOrderById]: " +
//                "Попытка получить информацию о заказе по id {}", id);
//        ResponseDeliveryOrder deliveryOrder = deliveryService
//                .getOrderByIdDelivery(id);
//        return new ResponseEntity<>(deliveryOrder, HttpStatus.OK);
//    }


//    @Operation(summary = "Обновить статус заказа доставки")
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateDeliveryOrderStatus(
//            @RequestBody RequestOrderStatus requestDeliveryStatus,
//            @PathVariable(name = "id") Long id) {
//        log.info("[DeliveryController:updateDeliveryOrderStatus]: " +
//                        "Попытка изменить статус доставки для заказа с id {} на статус {}"
//                , id, requestDeliveryStatus.getStatus().toString());
//        deliveryService.updateDeliveryOrderStatus(requestDeliveryStatus, id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
