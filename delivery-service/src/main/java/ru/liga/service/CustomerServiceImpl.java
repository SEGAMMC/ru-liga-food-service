package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.request.RequestCustomer;
import ru.liga.dto.response.ResponseCourier;
import ru.liga.dto.response.ResponseCustomer;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.repository.mybatis.CustomerRepositoryBatis;
import ru.liga.service.interfaces.CourierService;
import ru.liga.service.interfaces.CustomerService;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepositoryBatis customerRepositoryBatis;

    @Override
    public ResponseCustomer getCustomerById(Long id) {
        Customer customer = customerRepositoryBatis.getCustomerById(id);
        ResponseCustomer responseCustomer = new ResponseCustomer();
        responseCustomer.setId(customer.getId());
        responseCustomer.setPhone(customer.getPhone());
        responseCustomer.setEmail(customer.getEmail());
        responseCustomer.setAddress(customer.getAddress());
        return responseCustomer;
    }

    @Override
    public ResponseCustomer getCustomerByEmail(String email) {
        Customer customer = customerRepositoryBatis.getCustomerByEmail(email);
        ResponseCustomer responseCustomer = new ResponseCustomer();
        responseCustomer.setId(customer.getId());
        responseCustomer.setPhone(customer.getPhone());
        responseCustomer.setEmail(customer.getEmail());
        responseCustomer.setAddress(customer.getAddress());
        return responseCustomer;
    }

    @Override
    public ResponseCustomer updateCustomerPhone(RequestCustomer requestCustomer) {
        Customer customer = customerRepositoryBatis.getCustomerById(requestCustomer.getId());
        customer.setPhone(requestCustomer.getPhone());
        customerRepositoryBatis.updateCustomerPhone(customer);
        ResponseCustomer responseCustomer = new ResponseCustomer();
        responseCustomer.setId(customer.getId());
        responseCustomer.setPhone(customer.getPhone());
        responseCustomer.setEmail(customer.getEmail());
        responseCustomer.setAddress(customer.getAddress());
        return responseCustomer;
    }
}
