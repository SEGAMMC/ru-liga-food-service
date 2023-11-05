package ru.liga.service.interfaces;


import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.ResponseDeliveryOrder;

import java.util.List;

public interface DeliveryService {

    List<ResponseDeliveryOrder> getOrdersByStatusDelivery(String status);
	
	ResponseDeliveryOrder getOrderByIdDelivery(long id);
	
    void updateDeliveryOrderStatus(RequestOrderStatus status, long id);

}