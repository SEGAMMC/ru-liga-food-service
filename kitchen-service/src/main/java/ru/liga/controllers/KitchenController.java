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
@RequestMapping("/kitchen")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Kitchen-service используется для работы с меню кухни")
public class KitchenController {
    private final KitchenService kitchenService;

    @Operation(summary = "Получить информация о блюде по ID")
    @GetMapping("/item/{id}")
    public ResponseEntity<ResponseMenuItem> getMenuItemById(
            @PathVariable(name = "id") long id) {
        ResponseMenuItem respMenuItem = kitchenService.getMenuItemById(id);
        return new ResponseEntity<>(respMenuItem, HttpStatus.OK);
    }

    @Operation(summary = "Добавить новое блюдо")
    @PostMapping("/item")
    public ResponseEntity<ResponseMenuItem> createNewMenuItem(
            @RequestBody RequestMenuItem requestMenuItem) {
        ResponseMenuItem respMenuItem = kitchenService
                .createNewMenuItem(requestMenuItem);
        return new ResponseEntity<>(respMenuItem, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменить информацию о блюде по ID")
    @PutMapping("/item")
    public ResponseEntity<ResponseMenuItem> editMenuItem(
            @RequestBody RequestMenuItem requestMenuItem,
            @RequestParam("id") long id) {
        ResponseMenuItem respMenuItem = kitchenService
                .editMenuItem(requestMenuItem, id);
        return new ResponseEntity<>(respMenuItem, HttpStatus.OK);
    }


    @Operation(summary = "Обновить статус заказа приготовления")
    @PutMapping("/order/{id}")
    public ResponseEntity<Void> updateOrderStatusByKitchen(
            @RequestBody RequestOrderStatus requestOrderStatus,
            @PathVariable(name = "id") long id) {
        kitchenService.updateOrderStatusByKitchen(requestOrderStatus, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Получить список заказов имеющие соответствующий статус")
    @GetMapping("/orders")
    public ResponseEntity<ResponseOrdersList> getKitchenOrdersByStatus(
            @RequestParam(name = "status") String status) {
        ResponseOrdersList kitchenOrderList = kitchenService
                .getOrdersByStatusKitchen(status);
        return new ResponseEntity<>(kitchenOrderList, HttpStatus.OK);
    }

    @Operation(summary = "Изменить цену блюда в меню")
    @PutMapping("/item/{id}")
    public ResponseEntity<Void> updatePriceByMenuItem(
            @RequestBody RequestUpdatePriceMenuItem requestUpdatePriceMenuItem,
            @PathVariable(name = "id") long id) {
        kitchenService.updatePriceByMenuItem(requestUpdatePriceMenuItem, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}