package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.request.RequestCourierStatus;
import ru.liga.dto.response.ResponseCourier;
import ru.liga.service.interfaces.CourierService;

@RestController
@RequestMapping("/courier")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Courier-service используется для работы с курьерами")
public class CourierController {
    private final CourierService courierService;

    @Operation(summary = "Получить информацию о курьере по ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCourier> getCourierById(
            @PathVariable(name = "id") long id) {
        log.info("[CourierController:getCourierById]: " +
                "Попытка получить инфортмацию о курьере с id {}", id);
        ResponseCourier courier = courierService.getCourierById(id);
        return new ResponseEntity<>(courier, HttpStatus.OK);
    }

    @Operation(summary = "Добавить нового курьера")
    @PostMapping
    public ResponseEntity<ResponseCourier> createNewCourier(
		@RequestBody RequestCourier requestCourier) {
        log.info("[CourierController:createNewCourier]: " +
                "Попытка зарегистрировать нового курьера с информацией - {}"
                , requestCourier.toString());
        ResponseCourier responseCourier = courierService
				.createNewCourier(requestCourier);
        return new ResponseEntity<>(responseCourier, HttpStatus.CREATED);
    }

    @Operation(summary = "Обновить статус курьера")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCourierStatus(
			@PathVariable long id,
            @RequestBody RequestCourierStatus requestCourierStatus) {
        log.info("[CourierController:updateCourierStatus]: " +
                "Попытка изменить статус курьера с id {} на {}"
                , id, requestCourierStatus.getStatus().toString());
        courierService.updateCourierStatus(requestCourierStatus, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Получить информацию о курьере по номеру телефона")
    @GetMapping("/findbyphone/{phone}")
    public ResponseEntity<ResponseCourier> getCourierByPhone(
			@PathVariable(name = "phone") String phone) {
        log.info("[CourierController:getCourierByPhone]: " +
                "Попытка получить информацию о курьере но номеру телефона {}", phone);
        ResponseCourier courier = courierService.getCourierByPhone(phone);
        return new ResponseEntity<>(courier, HttpStatus.OK);
    }
}