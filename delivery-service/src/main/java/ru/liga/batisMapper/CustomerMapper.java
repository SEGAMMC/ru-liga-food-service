package ru.liga.batisMapper;

import ru.liga.entity.Customer;

public interface CustomerMapper {

    Customer getCustomerById(Long id);

    Customer getCustomerByPhone(String phone);

    Customer getCustomerByEmail(String email);

    void updateCustomerPhone(Customer Customer);

}
