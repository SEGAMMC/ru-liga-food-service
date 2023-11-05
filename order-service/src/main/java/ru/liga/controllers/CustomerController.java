package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestCustomer;
import ru.liga.dto.response.*;
import ru.liga.service.CustomerService;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Customer-service используется для взаимодействия с клиентами")
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "Получить информацию о клиенте")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCustomerProfile> getOrderById(
            @PathVariable(name = "id") long id) {
        ResponseCustomerProfile responseCustomer = customerService.getCustomerById(id);
        return new ResponseEntity<>(responseCustomer, HttpStatus.OK);
    }

    @Operation(summary = "Зарегистрировать нового клиента")
    @PostMapping
    public ResponseEntity<Void> createNewCustomer(
            @RequestBody RequestCustomer requestCustomer) {
        customerService.createNewCustomer(requestCustomer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Изменить информацию о клиенте")
    @PutMapping("/{id}")
    public ResponseEntity<Void> editCustomer(
            @PathVariable(name = "id") long id
            , @RequestBody RequestCustomer requestCustomer) {
        customerService.editCustomerById(requestCustomer, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}