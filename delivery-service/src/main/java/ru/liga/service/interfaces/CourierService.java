package ru.liga.service.interfaces;

import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.request.RequestCourierStatus;
import ru.liga.dto.response.ResponseCourier;

public interface CourierService {
	
    ResponseCourier getCourierById(Long id);

    ResponseCourier createNewCourier(RequestCourier requestCourier);
	
    void updateCourierStatus(RequestCourierStatus requestCourierStatus, Long id);
	
    ResponseCourier getCourierByPhone(String phone);

}


