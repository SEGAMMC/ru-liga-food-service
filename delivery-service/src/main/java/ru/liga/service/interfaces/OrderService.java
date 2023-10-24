package ru.liga.service.interfaces;



public interface OrderService {
    //TODO ругается на enum status
    ResponseOrder getOrderByIdBatis(long id);

    ResponseOrder getOrderById(long id);

    ResponseOrderWithStatus updateOrderStatus(RequestOrderWithStatus order);
}


