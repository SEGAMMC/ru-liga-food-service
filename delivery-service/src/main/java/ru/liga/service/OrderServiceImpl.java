package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestOrderWithStatus;
import ru.liga.dto.response.ResponseOrder;
import ru.liga.dto.response.ResponseOrderWithStatus;
import ru.liga.entity.*;
import ru.liga.repository.hibernate.OrdersRepository;
import ru.liga.repository.mybatis.OrderRepositoryBatis;
import ru.liga.service.interfaces.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepositoryBatis orderRepositoryBatis;
    private  final  OrdersRepository ordersRepository;

    @Override
    public ResponseOrder getOrderByIdBatis(long id) {
        Order order = orderRepositoryBatis.getOrderById(id);
        ResponseOrder responseOrder = new ResponseOrder();
        responseOrder.setId(order.getOrderId());
        ResponseOrder.ResponseRestaurant restaurant = new
                ResponseOrder.ResponseRestaurant(order.getRestaurantId().getName());
        responseOrder.setRestaurant(restaurant);
        responseOrder.setItems(order.getOrderItems());
        responseOrder.setTimestamp(order.getTimeStamp());
        return responseOrder;
    }

    @Override
    public ResponseOrder getOrderById(long id) {
        Order order = ordersRepository.findById(id).get();

        ResponseOrder responseOrder = new ResponseOrder();
        responseOrder.setId(order.getOrderId());
        ResponseOrder.ResponseRestaurant restaurant = new
                ResponseOrder.ResponseRestaurant(order.getRestaurantId().getName());
        responseOrder.setRestaurant(restaurant);
        responseOrder.setItems(order.getOrderItems());
        responseOrder.setTimestamp(order.getTimeStamp());
        return responseOrder;
    }

    @Override
    public ResponseOrderWithStatus updateOrderStatus(RequestOrderWithStatus requestOrder) {
        Order order = orderRepositoryBatis.getOrderById(requestOrder.getRestaurant_id());
        order.setStatus(requestOrder.getStatus());
        orderRepositoryBatis.updateOrderStatus(order);

        ResponseOrderWithStatus responseOrder = new ResponseOrderWithStatus();
        responseOrder.setId(order.getOrderId());
        ResponseOrderWithStatus.ResponseRestaurant restaurant = new
                ResponseOrderWithStatus.ResponseRestaurant(order.getRestaurantId().getName());
        responseOrder.setRestaurant(restaurant);
        responseOrder.setItems(order.getOrderItems());
        responseOrder.setTimestamp(order.getTimeStamp());
        return responseOrder;
    }



}
