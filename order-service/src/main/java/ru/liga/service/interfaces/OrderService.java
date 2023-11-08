package ru.liga.service.interfaces;

import ru.liga.dto.request.RequestOrder;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.request.RequestPay;
import ru.liga.dto.response.*;

import java.util.List;

public interface OrderService {
    ResponseOrderAccept createNewOrder(RequestOrder requestOrder);

    ResponseOrdersList getOrdersByStatusCustomer(String status);

    ResponseOrdersList getOrdersByStatusKitchen(String status);

    ResponseOrdersList getOrders();

    void updateOrderStatus(RequestOrderStatus requestOrderStatus);

    ResponseOrdersList getOrdersByCustomerId(long customerId);

    ResponseOrder getOrderById(long id);

    ResponseOrder getOrderByUuid(String id);

    ResponseOrderStatusByKitchen getOrderByUuidByKitchen(String id);

    ResponseOrderStatusByDelivery getOrderByUuidByDelivery(String uuid);

    List<ResponseDeliveryOrderForFindCourier> getOrdersByStatusDelivery();

    void payOrder(RequestPay requestPay);

    void cancelOrderById(String uuid);

    void updateOrderStatusByDelivery(RequestOrderStatus requestOrderStatus);

    void updateOrderStatusByKitchen(RequestOrderStatus requestOrderStatus);
}