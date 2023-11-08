package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestMenuItem;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.request.RequestUpdatePriceMenuItem;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.dto.response.ResponseOrdersList;
import ru.liga.service.interfaces.KitchenService;

import java.util.List;

@RestController
@RequestMapping("/api/kitchen")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Kitchen-service используется для работы с меню кухни и заказами")
public class KitchenController {
    private final KitchenService kitchenService;

    @Operation(summary = "Обновить статус заказа (принять/accept)")
    @PutMapping("/{uuid}/accept")
    public ResponseEntity<Void> updateOrderStatusByKitchenAccept(
            @PathVariable(name = "uuid") String uuid) {
        log.info("[KitchenController:updateOrderStatusByKitchenAccept]: " +
                        "Попытка обновить статус заказа id {} на новый  статус Accept"
                , uuid);
        kitchenService.updateOrderStatusByKitchenAccept( uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Обновить статус заказа (отклонить/decline)")
    @PutMapping("/{uuid}/decline")
    public ResponseEntity<Void> updateOrderStatusByKitchenDecline(
            @PathVariable(name = "uuid") String uuid) {
        log.info("[KitchenController:updateOrderStatusByKitchenDecline]: " +
                        "Попытка обновить статус заказа id {} на новый  статус Decline"
                , uuid);
        kitchenService.updateOrderStatusByKitchenDecline( uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Обновить статус заказа (готов/ready)")
    @PutMapping("/{uuid}/ready")
    public ResponseEntity<Void> updateOrderStatusByKitchenReady(
            @PathVariable(name = "uuid") String uuid) {
        log.info("[KitchenController:updateOrderStatusByKitchenReady]: " +
                        "Попытка обновить статус заказа id {} на новый  статус Ready"
                , uuid);
        kitchenService.updateOrderStatusByKitchenReady( uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Получить список заказов имеющие соответствующий статус")
    @GetMapping("/orders")
    public ResponseEntity<ResponseOrdersList> getKitchenOrdersByStatus(
            @RequestParam(name = "status") String status) {
        log.info("[KitchenController:getKitchenOrdersByStatus]: " +
                "Попытка получить список заказов имеющие статус {}", status);
        ResponseOrdersList kitchenOrderList = kitchenService
                .getOrdersByStatusKitchen(status);
        return new ResponseEntity<>(kitchenOrderList, HttpStatus.OK);
    }

    @Operation(summary = "Получить информация о блюде по ID")
    @GetMapping("/item/{id}")
    public ResponseEntity<ResponseMenuItem> getMenuItemById(
            @PathVariable(name = "id") long id) {
        log.info("[KitchenController:getMenuItemById]:" +
                "Попытка получить информацию о блюде с id {}", id);
        ResponseMenuItem respMenuItem = kitchenService.getMenuItemById(id);
        return new ResponseEntity<>(respMenuItem, HttpStatus.OK);
    }

    @Operation(summary = "Добавить новое блюдо")
    @PostMapping("/item")
    public ResponseEntity<ResponseMenuItem> createNewMenuItem(
            @RequestBody RequestMenuItem requestMenuItem) {
        log.info("[KitchenController:createNewMenuItem]:" +
                "Попытка регистрации нового блюда с информацией {}"
                , requestMenuItem.toString());
        ResponseMenuItem respMenuItem = kitchenService
                .createNewMenuItem(requestMenuItem);
        return new ResponseEntity<>(respMenuItem, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменить информацию о блюде по ID")
    @PutMapping("/item")
    public ResponseEntity<ResponseMenuItem> editMenuItem(
            @RequestBody RequestMenuItem requestMenuItem,
            @RequestParam("id") long id) {
        log.info("[KitchenController:editMenuItem]: " +
                "Попытка изменить информацию о блюде с id {}, на информацию - "
                , requestMenuItem.toString());
        ResponseMenuItem respMenuItem = kitchenService
                .editMenuItem(requestMenuItem, id);
        return new ResponseEntity<>(respMenuItem, HttpStatus.OK);
    }

    @Operation(summary = "Изменить цену блюда в меню")
    @PutMapping("/item/{id}")
    public ResponseEntity<Void> updatePriceByMenuItem(
            @RequestBody RequestUpdatePriceMenuItem requestUpdatePriceMenuItem,
            @PathVariable(name = "id") long id) {
        log.info("[KitchenController:updatePriceByMenuItem]:" +
                "Попытка изменить цену блюда с id {} на новую цену {}"
                , id, requestUpdatePriceMenuItem.getPrice());
        kitchenService.updatePriceByMenuItem(requestUpdatePriceMenuItem, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}