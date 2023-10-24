package ru.liga.batisMapper.customer;

import ru.liga.entity.Customer;

public interface CustomerMapper {

    Customer getCustomerById(Long id);

    Customer getCustomerByEmail(String email);

    void updateCustomerPhone(Customer Customer);

}
