package ru.liga.service;

import org.springframework.stereotype.Service;
import ru.liga.controllers.request.RequestOrder;
import ru.liga.controllers.response.ResponseItem;
import ru.liga.controllers.response.ResponseOrder;
import ru.liga.controllers.response.ResponseOrderAccept;
import ru.liga.controllers.response.ResponseOrdersList;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.repository.OrdersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrdersRepository ordersRepository;

    public OrderService(OrdersRepository repository) {
        this.ordersRepository = repository;
    }

    public ResponseOrdersList getOrders() {
        List<Order> ordersInDatabase = ordersRepository.findAll();
        List<ResponseOrder> ordersForResponse = new ArrayList<>();

        for (Order orderDB : ordersInDatabase) {
            ResponseOrder respOrder = new ResponseOrder();
            respOrder.setId(orderDB.getId());
            respOrder.setRestaurantByOrder(orderDB.getRestaurantId());
            respOrder.setTimestamp(orderDB.getTimeStamp());

            respOrder.setItems(forechItem(orderDB.getOrderItems()));
            ordersForResponse.add(respOrder);
        }
        ResponseOrdersList respOrdersList = new ResponseOrdersList();
        respOrdersList.setOrders(ordersForResponse);
        return respOrdersList;
    }

    private static List<ResponseItem> forechItem(List<OrderItem> orderItems) {
        List<ResponseItem> respItems = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            ResponseItem item = new ResponseItem();
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
        respOrder.setId(orderById.getId());
        respOrder.setRestaurantByOrder(orderById.getRestaurantId());
        respOrder.setTimestamp(orderById.getTimeStamp());

        respOrder.setItems(forechItem(orderById.getOrderItems()));
        return respOrder;
    }

    public ResponseOrderAccept createNewOrder(RequestOrder requestOrder) {
        Order order = new Order();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(requestOrder.getRestaurant_id());
        order.setRestaurantId(restaurant);
        List<OrderItem> orderItemsList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        RestaurantMenuItem rmi = new RestaurantMenuItem();

        for (RequestOrder.RequestItemsList requestItem:requestOrder.getMenu_items()) {
            orderItem.setQuantity(requestItem.getQuantity());
            rmi.setId(requestItem.getMenu_item_id());
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
}
