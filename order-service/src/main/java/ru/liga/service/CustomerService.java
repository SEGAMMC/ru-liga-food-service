package ru.liga.service;


import ru.liga.dto.request.RequestCustomer;
import ru.liga.dto.response.ResponseCustomerProfile;

public interface CustomerService {

    ResponseCustomerProfile getCustomerById(long id);

    void createNewCustomer (RequestCustomer requestCustomer);

    void editCustomerById(RequestCustomer requestCustomer, long id);

}