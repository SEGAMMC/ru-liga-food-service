package ru.liga.service.interfaces;

import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.request.RequestCourierStatus;
import ru.liga.dto.response.ResponseCourier;

public interface CourierService {
	
    ResponseCourier getCourierById(long id);

    ResponseCourier createNewCourier(RequestCourier requestCourier);
	
    void updateCourierStatus(RequestCourierStatus requestCourierStatus, long id);
	
    ResponseCourier getCourierByPhone(String phone);

}