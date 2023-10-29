package ru.liga.controllers;

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
public class CourierController {
    private final CourierService courierService;

    @PostMapping
    public ResponseEntity<?> createNewCourier(@RequestBody RequestCourier requestCourier) {
        ResponseCourier responseCourier = courierService.createNewCourier(requestCourier);
        return (responseCourier != null)
                ? new ResponseEntity<>(responseCourier, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //TODO вылетает ошибка, разобраться
//    @GetMapping("/get/${idc}")
//    public ResponseEntity<?> getCourierById(@PathVariable(name = "idc") Long id) {
//        ResponseCourier courier = courierService.getCourierById(id);
//        return (courier != null)
//                ? new ResponseEntity<>(courier, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateCourierStatus(@PathVariable long id,
                                                 @RequestBody RequestCourierStatus requestCourierStatus) {
        courierService.updateCourierStatus(requestCourierStatus, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> getCourierByPhone(@PathVariable(name = "phone") String phone) {
        ResponseCourier courier = courierService.getCourierByPhone(phone);
        return (courier != null)
                ? new ResponseEntity<>(courier, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
