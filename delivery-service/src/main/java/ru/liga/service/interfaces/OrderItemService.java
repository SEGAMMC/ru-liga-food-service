package ru.liga.service.interfaces;

import ru.liga.dto.request.RequestNewOrderItem;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.dto.response.ResponseOrderItem;

public interface OrderItemService {
    ResponseOrderItem getOrderItemById(long id);

    ResponseMenuItem getMenuItem(long id);

    ResponseOrderItem createNewOrderItem (RequestNewOrderItem orderItem);
}


