package ru.liga.service;

import ru.liga.dto.RequestOrder;
import ru.liga.dto.ResponseOrder;
import ru.liga.dto.ResponseOrderAccept;
import ru.liga.dto.ResponseOrdersList;
import ru.liga.entity.Order;

public interface OrderService {
    ResponseOrdersList getOrders();

    ResponseOrder getOrderById(long id);

//    ResponseOrderAccept createNewOrder(RequestOrder requestOrder);
    Order createNewOrder(RequestOrder requestOrder);

    ResponseOrder updateOrder(RequestOrder requestOrder);

    ResponseOrder deleteOrderById(String id);
}


