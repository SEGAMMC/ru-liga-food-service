package ru.liga.batisMapper.courier;

import ru.liga.entity.Courier;

public interface CourierMapper {
    Courier getCourierById(Long id);

    Courier getCourierByPhone(String phone);

    void updateCourierStatus(Courier courier);
}
