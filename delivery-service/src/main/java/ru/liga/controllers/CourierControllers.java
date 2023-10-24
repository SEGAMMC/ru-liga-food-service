package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.response.ResponseCourier;
import ru.liga.service.interfaces.CourierService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courier")
@Slf4j
public class CourierControllers {
    private final CourierService courierService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourierById(@PathVariable(name = "id") Long id) {
        ResponseCourier courier = courierService.getCourierById(id);
        return (courier != null)
                ? new ResponseEntity<>(courier, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> getCourierByPhone(@PathVariable(name = "phone") String phone) {
        ResponseCourier courier = courierService.getCourierByPhone(phone);
        return (courier != null)
                ? new ResponseEntity<>(courier, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateCourierStatus(@RequestBody RequestCourier requestCourier) {
        ResponseCourier responseCourier = courierService.updateCourierStatus(requestCourier);
        return (responseCourier != null)
                ? new ResponseEntity<>(responseCourier, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
