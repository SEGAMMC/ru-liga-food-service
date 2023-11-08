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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order-service используется для работы с заказами на доставку")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Добавить новый заказ")
    @PostMapping()
    public ResponseEntity<ResponseOrderAccept> createNewOrder(
            @RequestBody RequestOrder requestOrder) {
        log.info("[OrderController:createNewOrder]:" +
                " Попытка оформить новый заказ для клиента с id {}," +
                " информация о заказе {}", requestOrder.getCustomerId(), requestOrder.toString());
        ResponseOrderAccept responseOrder = orderService
                .createNewOrder(requestOrder);
        return new ResponseEntity<>(responseOrder, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить заказ по UUID")
    @GetMapping("/{uuid}")
    public ResponseEntity<ResponseOrder> getOrderByUuid(
            @PathVariable(name = "uuid") String uuid) {
        log.info("[OrderController:getOrderByUuid]: " +
                "Попытка получить информацию о заказе с uuid {}", uuid);
        ResponseOrder responseOrder = orderService.getOrderByUuid(uuid);
        return new ResponseEntity<>(responseOrder, HttpStatus.OK);
    }


    //-------------------------------------------------------------------

    @Operation(summary = "Оплатить заказ по UUID")
    @PutMapping("/pay")
    public ResponseEntity<Void> payOrder(
            @RequestBody RequestPay requestPay) {
        log.info("[OrderController:payOrder]:" +
                " Попытка оплатить заказ {},", requestPay.getUuid());
        orderService.payOrder(requestPay);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Отменить заказ")
    @PutMapping("/{uuid}/cancel")
    public ResponseEntity<Void> cancelOrderById(
            @PathVariable(name = "uuid") String uuid) {
        log.info("[OrderController:cancelOrderById]: " +
                        "Попытка отменить заказ по uuid {}" , uuid);
        orderService.cancelOrderById(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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

    @Operation(summary = "Получить заказ по ID")
    @GetMapping("/byid/{id}")
    public ResponseEntity<ResponseOrder> getOrderById(
            @PathVariable(name = "id") long id) {
        log.info("[OrderController:getOrderById]: " +
                "Попытка получить информацию о заказе с id {}", id);
        ResponseOrder responseOrder = orderService.getOrderById(id);
        return new ResponseEntity<>(responseOrder, HttpStatus.OK);
    }






    @Operation(summary = "Получить заказ по UUID")
    @GetMapping("/{uuid}/kitchen")
    public ResponseOrderStatusByKitchen getOrderByUuidByKitchen(
            @PathVariable(name = "uuid") String uuid) {
        log.info("[OrderController:getOrderByUuidByKitchen]: " +
                "Попытка получить информацию о заказе с uuid {}", uuid);
        ResponseOrderStatusByKitchen responseOrder = orderService.getOrderByUuidByKitchen(uuid);
        return responseOrder;
    }

    @Operation(summary = "Получить заказ по UUID")
    @GetMapping("/{uuid}/delivery")
    public ResponseOrderStatusByDelivery getOrderByUuidByDelivery(
            @PathVariable(name = "uuid") String uuid) {
        log.info("[OrderController:getOrderByUuidByDelivery]: " +
                "Попытка получить информацию о заказе с uuid {}", uuid);
        ResponseOrderStatusByDelivery responseOrder = orderService.getOrderByUuidByDelivery(uuid);
        return responseOrder;
    }


    @Operation(summary = "Обновить статус заказа для кухни")
    @PutMapping("/updateStatusKitchen")
    public ResponseEntity<Void> updateOrderStatusByKitchen(
            @RequestBody RequestOrderStatus requestOrderStatus) {
        log.info("[OrderController:updateOrderStatusByKitchen]: " +
                        "Попытка обновить статус заказа id {}, новый статус {}"
                , requestOrderStatus.getUuid(), requestOrderStatus.getStatus());
        orderService.updateOrderStatusByKitchen(requestOrderStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Обновить статус заказа для доставки")
    @PutMapping("/updateStatusDelivery")
    public ResponseEntity<Void> updateOrderStatusByDelivery(
            @RequestBody RequestOrderStatus requestOrderStatus) {
        log.info("[OrderController:updateOrderStatusByDelivery]: " +
                        "Попытка обновить статус заказа id {}, новый статус {}"
                , requestOrderStatus.getUuid(), requestOrderStatus.getStatus());
        orderService.updateOrderStatusByDelivery(requestOrderStatus);
        return new ResponseEntity<>(HttpStatus.OK);
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
    public List<ResponseDeliveryOrderForFindCourier> getOrdersByStatusDeliveryPending() {
        log.info("[OrderController:getOrdersByStatusDeliveryPending]:" +
                " Попытка получить список заказов имеющие статус Pending для доставки");
        List<ResponseDeliveryOrderForFindCourier> responseOrdersList = orderService
                .getOrdersByStatusDelivery();
        return responseOrdersList;
    }

}