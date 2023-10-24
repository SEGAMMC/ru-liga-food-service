package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestCustomer;
import ru.liga.dto.response.ResponseCustomer;
import ru.liga.service.interfaces.CustomerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@Slf4j
public class CustomerControllers {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable(name = "id") Long id) {
        ResponseCustomer customer = customerService.getCustomerById(id);
        return (customer != null)
                ? new ResponseEntity<>(customer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable(name = "email") String email) {
        ResponseCustomer customer = customerService.getCustomerByEmail(email);
        return (customer != null)
                ? new ResponseEntity<>(customer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateCustomerPhone(@RequestBody RequestCustomer requestCustomer) {
        ResponseCustomer responseCustomer = customerService.updateCustomerPhone(requestCustomer);
        return (responseCustomer != null)
                ? new ResponseEntity<>(responseCustomer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
