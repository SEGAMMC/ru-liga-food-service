package ru.liga.service;

import ru.liga.dto.request.RequestOrder;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.ResponseOrder;
import ru.liga.dto.response.ResponseOrderAccept;
import ru.liga.dto.response.ResponseOrdersList;

public interface OrderService {
    ResponseOrdersList getOrders();

    ResponseOrdersList getOrdersByCustomerId(long customerId);
    void updateOrderStatus(RequestOrderStatus requestOrderStatus, long id);

    ResponseOrder getOrderById(long id);
    ResponseOrderAccept createNewOrder(RequestOrder requestOrder, long customerId);

    ResponseOrdersList getOrdersByStatusCustomer(String status);

    ResponseOrdersList getOrdersByStatusKitchen(String status);

    ResponseOrdersList getOrdersByStatusDelivery(String status);
}