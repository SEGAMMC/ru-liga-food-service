package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.*;
import ru.liga.dto.response.*;
import ru.liga.service.interfaces.OrderService;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "Order-service используется для работы с заказами на доставку")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Получить список всех заказов")
    @GetMapping("/all")
    public ResponseEntity<ResponseOrdersList> getOrders() {
        ResponseOrdersList responseOrdersList = orderService.getOrders();
        return new ResponseEntity<>(responseOrdersList, HttpStatus.OK);
    }

    @Operation(summary = "Получить список заказов конкретного пользователя")
    @GetMapping
    public ResponseEntity<ResponseOrdersList> getOrdersByCustomerId(
            @RequestParam(name = "customer") long id) {
        log.info("[OrderController:getOrdersByCustomerId]: " +
                "Попытка получить список заказов пользователя с id {}", id);
        ResponseOrdersList responseOrdersList = orderService.getOrdersByCustomerId(id);
        return new ResponseEntity<>(responseOrdersList, HttpStatus.OK);
    }

    @Operation(summary = "Обновить статус заказа")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderStatus(
            @RequestBody RequestOrderStatus requestOrderStatus
            , @PathVariable(name = "id") long id) {
        log.info("[OrderController:updateOrderStatus]: " +
                "Попытка обновить статус заказа id {}, новый статус {}"
                , id, requestOrderStatus.getStatus().toString());
        orderService.updateOrderStatus(requestOrderStatus, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Получить заказ по ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrder> getOrderById(
            @PathVariable(name = "id") long id) {
        log.info("[OrderController:getOrderById]: " +
                "Попытка получить информацию о заказе с id {}", id);
        ResponseOrder responseOrder = orderService.getOrderById(id);
        return new ResponseEntity<>(responseOrder, HttpStatus.OK);
    }

    @Operation(summary = "Добавить новый заказ")
    @PostMapping("/{id}")
    public ResponseEntity<ResponseOrderAccept> createNewOrder(
            @RequestBody RequestOrder requestOrder,
            @PathVariable(name = "id") long customerId) {
        log.info("[OrderController:createNewOrder]:" +
                " Попытка оформить новый заказ для клиента с id {}," +
                " информация о заказе {}", customerId, requestOrder.toString());
        ResponseOrderAccept responseOrder = orderService
                .createNewOrder(requestOrder, customerId);
        return new ResponseEntity<>(responseOrder, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить список заказов имеющие соответствующий статус для заказчика")
    @GetMapping("/customers")
    public ResponseEntity<ResponseOrdersList> getOrdersByStatusCustomer(
            @RequestParam(name = "status") String status) {
        log.info("[OrderController:getOrdersByStatusCustomer]:" +
                " Попытка получить список заказов имеющие статус {}," +
                " для зазказчиков", status);
        ResponseOrdersList responseOrdersList = orderService
                .getOrdersByStatusCustomer(status);
        return new ResponseEntity<>(responseOrdersList, HttpStatus.OK);
    }

    @Operation(summary = "Получить список заказов имеющие соответствующий статус для кухни")
    @GetMapping("/kitchens")
    public ResponseEntity<ResponseOrdersList> getOrdersByStatusKitchen(
            @RequestParam(name = "status") String status) {
        log.info("[OrderController:getOrdersByStatusKitchen]:" +
                " Попытка получить список заказов имеющие статус {}," +
                " для кухни", status);
        ResponseOrdersList responseOrdersList = orderService
                .getOrdersByStatusKitchen(status);
        return new ResponseEntity<>(responseOrdersList, HttpStatus.OK);
    }

    @Operation(summary = "Получить список заказов имеющие соответствующий статус для доставки")
    @GetMapping("/deliveries")
    public ResponseEntity<ResponseOrdersList> getOrdersByStatusDelivery(
            @RequestParam(name = "status") String status) {
        log.info("[OrderController:getOrdersByStatusDelivery]:" +
                " Попытка получить список заказов имеющие статус {}," +
                " для доставки", status);
        ResponseOrdersList responseOrdersList = orderService
                .getOrdersByStatusDelivery(status);
        return new ResponseEntity<>(responseOrdersList, HttpStatus.OK);
    }

}