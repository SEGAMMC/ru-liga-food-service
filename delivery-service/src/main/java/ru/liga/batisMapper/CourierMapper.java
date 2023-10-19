package ru.liga.batisMapper;

import ru.liga.entity.Courier;
import ru.liga.entity.enums.CourierStatus;

import java.util.List;

public interface CourierMapper {

    Courier getCourierById(Long id);

    List<Courier> getCourierByStatus(CourierStatus status);

    Courier getCourierByPhone(String phone);

    void updateCourierStatus(Courier courier);
}
