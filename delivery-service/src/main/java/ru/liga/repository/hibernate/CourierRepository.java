package ru.liga.repository.hibernate;

import org.springframework.data.repository.CrudRepository;
import ru.liga.entity.Courier;

public interface CourierRepository
        extends CrudRepository<Courier, Long> {

}
