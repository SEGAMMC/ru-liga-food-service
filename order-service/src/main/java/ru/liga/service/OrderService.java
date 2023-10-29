package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.response.*;
import ru.liga.dto.request.*;
import ru.liga.entity.*;
import ru.liga.enums.OrderStatus;
import ru.liga.repository.OrdersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;

    public ResponseOrdersList getOrders() {
        List<Order> ordersInDatabase = ordersRepository.findAll();
        List<ResponseOrder> ordersForResponse = new ArrayList<>();

        for (Order orderDB : ordersInDatabase) {
            ResponseOrder respOrder = new ResponseOrder();
            ResponseRestaurantName respRestaurantName = new ResponseRestaurantName();
            respRestaurantName.setName(orderDB.getRestaurantId().getName());
            respOrder.setId(orderDB.getId());
            respOrder.setRestaurant(respRestaurantName);
            respOrder.setTimestamp(orderDB.getTimeStamp());

            respOrder.setItems(forechItem(orderDB.getOrderItems()));
            ordersForResponse.add(respOrder);
        }
        ResponseOrdersList respOrdersList = new ResponseOrdersList();
        respOrdersList.setOrders(ordersForResponse);
        return respOrdersList;
    }

    private static List<ResponseOrderItem> forechItem(List<OrderItem> orderItems) {
        List<ResponseOrderItem> respItems = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            ResponseOrderItem item = new ResponseOrderItem();
            item.setPrice(orderItem.getPrice());
            item.setQuantity(orderItem.getQuantity());
            item.setDescription(orderItem.getRestaurantMenuItem().getDescription());
            item.setImage(orderItem.getRestaurantMenuItem().getImage());
            respItems.add(item);
        }
        return respItems;
    }

    public ResponseOrder getOrderById(long id) {
        Order orderById = ordersRepository.findById(id).orElseThrow();
        ResponseOrder respOrder = new ResponseOrder();
        ResponseRestaurantName respRestaurantName = new ResponseRestaurantName();
        respRestaurantName.setName(orderById.getRestaurantId().getName());

        respOrder.setId(orderById.getId());
        respOrder.setRestaurant(respRestaurantName);
        respOrder.setTimestamp(orderById.getTimeStamp());

        respOrder.setItems(forechItem(orderById.getOrderItems()));
        return respOrder;
    }

    public ResponseOrderAccept createNewOrder(RequestOrder requestOrder) {
        Order order = new Order();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(requestOrder.getRestaurantId());
        order.setRestaurantId(restaurant);
        List<OrderItem> orderItemsList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        RestaurantMenuItem rmi = new RestaurantMenuItem();

        for (RequestOrderItems requestItem : requestOrder.getOrderItems()) {
            orderItem.setQuantity(requestItem.getQuantity());
            rmi.setId(requestItem.getMenuItemId());
            orderItem.setRestaurantMenuItem(rmi);
            orderItemsList.add(orderItem);
        }

        order.setOrderItems(orderItemsList);
        System.out.println(order);

        ResponseOrderAccept responseOrderAccept = new ResponseOrderAccept();
        //TODO добавить создание responsa
        return responseOrderAccept;
    }

    public ResponseOrder updateOrder(RequestOrder requestOrder) {
        return null;
    }

    public ResponseOrder deleteOrderById(String id) {
        return null;
    }

    public void updateOrderStatus(RequestOrderStatus requestOrderStatus, Long id) {
        Order order = ordersRepository.findById(id).get();
        order.setStatus(requestOrderStatus.getStatus());
        ordersRepository.save(order);
    }
}


