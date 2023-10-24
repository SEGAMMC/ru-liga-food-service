package ru.liga.repository.hibernate;

import org.springframework.data.repository.CrudRepository;
import ru.liga.entity.Customer;

public interface CustomerRepository
        extends CrudRepository<Customer, Long> {

}
