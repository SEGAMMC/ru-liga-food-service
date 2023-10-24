package ru.liga.service.interfaces;

import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.response.ResponseCourier;

public interface CourierService {
    ResponseCourier getCourierById(Long id);

    ResponseCourier getCourierByPhone(String phone);

    ResponseCourier updateCourierStatus(RequestCourier requestCourier);

}


