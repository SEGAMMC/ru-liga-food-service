package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestCustomer;

import ru.liga.dto.response.*;
import ru.liga.entity.*;
import ru.liga.exception.CustomerNotFoundException;
import ru.liga.repository.hibernate.*;
import ru.liga.util.Validator;



@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final Validator validator;
    private final CustomerRepository customerRepository;

    @Override
    public ResponseCustomerProfile getCustomerById(long id) {
        validator.isPositive(id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        ResponseCustomerProfile responseCustomer = new ResponseCustomerProfile();
        responseCustomer.setEmail(customer.getEmail());
        responseCustomer.setPhone(customer.getPhone());
        responseCustomer.setAddress(customer.getAddress());
        return responseCustomer;
    }

    @Override
    public void createNewCustomer(RequestCustomer requestCustomer) {
        Customer customer = new Customer();
        customer.setPhone(requestCustomer.getPhone());
        customer.setEmail(requestCustomer.getEmail());
        customer.setAddress(requestCustomer.getAddress());
        customerRepository.save(customer);
    }

    @Override
    public void editCustomerById(RequestCustomer requestCustomer, long id) {
        validator.isPositive(id);
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new CustomerNotFoundException(id));
        customer.setPhone(requestCustomer.getPhone());
        customer.setEmail(requestCustomer.getEmail());
        customer.setAddress(requestCustomer.getAddress());
        customerRepository.save(customer);
    }
}


