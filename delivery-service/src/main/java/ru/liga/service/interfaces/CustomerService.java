package ru.liga.service.interfaces;

import ru.liga.dto.request.RequestCustomer;
import ru.liga.dto.response.ResponseCustomer;

public interface CustomerService {
    ResponseCustomer getCustomerById(Long id);

    ResponseCustomer getCustomerByEmail(String phone);

    ResponseCustomer updateCustomerPhone(RequestCustomer requestCustomer);

}


