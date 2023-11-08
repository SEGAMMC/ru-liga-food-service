package ru.liga.service.interfaces;


import ru.liga.dto.response.ResponseDeliveryOrder;

import java.util.List;

public interface DeliveryService {

    List<ResponseDeliveryOrder> getDeliveryOrdersByStatusPending(long id);

    void updateOrderStatusByDeliveryTake(String uuid);

    void updateOrderStatusByDeliveryComplete(String uuid);
}