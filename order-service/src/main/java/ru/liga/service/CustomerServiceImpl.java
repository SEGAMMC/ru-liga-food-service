package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestCustomer;

import ru.liga.dto.response.*;
import ru.liga.entity.*;
import ru.liga.exception.CustomerNotFoundException;
import ru.liga.repository.hibernate.*;
import ru.liga.service.interfaces.CustomerService;
import ru.liga.util.Validator;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final Validator validator;
    private final CustomerRepository customerRepository;

    @Override
    public ResponseCustomerProfile getCustomerById(long id) {
        validator.isPositive(id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> (new CustomerNotFoundException(id)));
        ResponseCustomerProfile responseCustomer = new ResponseCustomerProfile();
        responseCustomer.setEmail(customer.getEmail());
        responseCustomer.setPhone(customer.getPhone());
        responseCustomer.setAddress(customer.getAddress());
        log.info("[CustomerServiceImpl:getCustomerById]:" +
                " Получили информацию о клиенте с id {}", id);
        return responseCustomer;
    }

    @Override
    public void createNewCustomer(RequestCustomer requestCustomer) {
        Customer customer = new Customer();
        customer.setPhone(requestCustomer.getPhone());
        customer.setEmail(requestCustomer.getEmail());
        customer.setAddress(requestCustomer.getAddress());
        customer = customerRepository.save(customer);
        log.info("[CustomerServiceImpl:createNewCustomer]:" +
                " Зарегистрировали нового клиента с id {}", customer.getId());
    }

    @Override
    public void editCustomerById(RequestCustomer requestCustomer, long id) {
        validator.isPositive(id);
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new CustomerNotFoundException(id));
        customer.setPhone(requestCustomer.getPhone());
        customer.setEmail(requestCustomer.getEmail());
        customer.setAddress(requestCustomer.getAddress());
        customer = customerRepository.save(customer);
        log.info("[CustomerServiceImpl:editCustomerById]:" +
                " Изменили информацию о клиенте с id {}", customer.getId());
    }
}


