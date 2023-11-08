package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestMenuItem;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.request.RequestUpdatePriceMenuItem;
import ru.liga.dto.response.*;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.enums.OrderStatus;
import ru.liga.exception.exceptions.MenuItemNotFoundException;
import ru.liga.exception.exceptions.RequestOrderStatusInvalidException;
import ru.liga.exception.exceptions.RestaurantNotFoundException;
import ru.liga.feign.FeignKitchen;
import ru.liga.repository.hibernate.MenuItemRepository;
import ru.liga.repository.hibernate.RestaurantRepository;
import ru.liga.service.interfaces.KitchenService;
import ru.liga.util.Validator;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final FeignKitchen feignKitchen;
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;
    private final Validator validator;
    private static final double MONEY_KOEFFICIENT = 100d;// в БД price в копейках


    @Override
    public void updateOrderStatusByKitchenAccept(String uuid) {
        ResponseOrderStatusByKitchen responseOrder = feignKitchen.getOrderByUuid(uuid);
        OrderStatus orderStatus = responseOrder.getOrderStatus();
        OrderStatus newStatus = null;
        if (orderStatus.equals(OrderStatus.CUSTOMER_PAID)) {
            newStatus = OrderStatus.KITCHEN_ACCEPTED;
            RequestOrderStatus requestOrderStatus = new RequestOrderStatus();
            requestOrderStatus.setStatus(newStatus);
            requestOrderStatus.setUuid(uuid);
            feignKitchen.updateOrderStatusByKitchen(requestOrderStatus);
            //todo отправить сообщение клиенту о статусе
            //            rabbitMQProducerService.sendOrderToDelivery(String
//                    .valueOf(order.getId()));
            log.info("[KitchenServiceImpl:updateOrderStatusByKitchenAccept]: " +
                    "Обновили статус заказа id {} на {}", uuid, newStatus.toString());
        } else {
            throw new RequestOrderStatusInvalidException(orderStatus.toString());
        }

    }


    @Override
    public void updateOrderStatusByKitchenDecline(String uuid) {
        ResponseOrderStatusByKitchen responseOrder = feignKitchen.getOrderByUuid(uuid);
        OrderStatus orderStatus = responseOrder.getOrderStatus();
        OrderStatus newStatus = null;
        if (orderStatus.equals(OrderStatus.CUSTOMER_PAID)) {
            newStatus = OrderStatus.KITCHEN_DENIED;
            RequestOrderStatus requestOrderStatus = new RequestOrderStatus();
            requestOrderStatus.setStatus(newStatus);
            requestOrderStatus.setUuid(uuid);
            feignKitchen.updateOrderStatusByKitchen(requestOrderStatus);
            //todo отправить сообщение клиенту о статусе
            //            rabbitMQProducerService.sendOrderToDelivery(String
//                    .valueOf(order.getId()));
            log.info("[KitchenServiceImpl:updateOrderStatusByKitchenDecline]: " +
                    "Обновили статус заказа id {} на {}", uuid, newStatus.toString());

        } else {
            throw new RequestOrderStatusInvalidException(orderStatus.toString());
        }

    }

    @Override
    public void updateOrderStatusByKitchenReady(String uuid) {
        OrderStatus newStatus = OrderStatus.DELIVERY_PENDING;
        RequestOrderStatus requestOrderStatus = new RequestOrderStatus();
        requestOrderStatus.setStatus(newStatus);
        requestOrderStatus.setUuid(uuid);
        feignKitchen.updateOrderStatusByKitchen(requestOrderStatus);
        if (requestOrderStatus.getStatus()
                .equals(OrderStatus.DELIVERY_PENDING)) {
            //TODO отправить сообщение клиенту
            //TODO отправить сообщение доставщику

            log.info("[KitchenServiceImpl:updateOrderStatusByKitchenReady]: " +
                    "Заказ {} ожидает доставки", uuid);

//            rabbitMQProducerService.sendOrderToDelivery(String
//                    .valueOf(order.getId()));
        }
        log.info("[KitchenServiceImpl:updateOrderStatusByKitchenReady]: " +
                "Обновили статус заказа id {} на {}", uuid, newStatus.toString());
    }


    @Override
    public ResponseOrdersList getOrdersByStatusKitchen(String status) {
        return getOrdersByStatus("KITCHEN_" + status.toUpperCase());
    }

    @Override
    public ResponseMenuItem getMenuItemById(long id) {
        validator.isPositive(id);
        RestaurantMenuItem restaurantMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuItemNotFoundException(id));
        log.info("[KitchenServiceImpl:getMenuItemById]: " +
                        "Получили информацию о блюде с id {} из ресторана {}"
                , id, restaurantMenuItem.getRestaurantId());
        return mapMenuItemToResponseMenuItem(restaurantMenuItem);
    }

    @Override
    public void updatePriceByMenuItem(RequestUpdatePriceMenuItem
                                              requestUpdatePriceMenuItem, long id) {
        validator.isPositive(id);
        validator.isPositive(requestUpdatePriceMenuItem.getPrice());
        RestaurantMenuItem restaurantMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuItemNotFoundException(id));
        restaurantMenuItem.setPrice(requestUpdatePriceMenuItem.getPrice());
        restaurantMenuItem = menuItemRepository.save(restaurantMenuItem);
        log.info("[KitchenServiceImpl:updatePriceByMenuItem]: " +
                "Изменили цену блюда id {}  на {}", id, restaurantMenuItem.getPrice());
    }

    @Override
    public ResponseMenuItem editMenuItem(RequestMenuItem requestMenuItem, long id) {
        validator.isPositive(id);
        validator.checkRequestMenuItem(requestMenuItem);
        RestaurantMenuItem restaurantMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuItemNotFoundException(id));
        restaurantMenuItem = mapRequestMenuItemToMenuItem(requestMenuItem
                , restaurantMenuItem);
        restaurantMenuItem = menuItemRepository.save(restaurantMenuItem);
        log.info("[KitchenServiceImpl:editMenuItem]: " +
                "Изменили информацию о блюде с id {}", restaurantMenuItem.getId());
        return mapMenuItemToResponseMenuItem(restaurantMenuItem);
    }

    @Override
    public ResponseMenuItem createNewMenuItem(RequestMenuItem requestMenuItem) {
        validator.checkRequestMenuItem(requestMenuItem);
        RestaurantMenuItem restaurantMenuItem = new RestaurantMenuItem();
        restaurantMenuItem = mapRequestMenuItemToMenuItem(requestMenuItem
                , restaurantMenuItem);
        restaurantMenuItem = menuItemRepository.save(restaurantMenuItem);
        log.info("[KitchenServiceImpl:createNewMenuItem]: " +
                "Зарегистрировали новое блюдо с id {}", restaurantMenuItem.getId());
        return mapMenuItemToResponseMenuItem(restaurantMenuItem);
    }

    private ResponseOrdersList getOrdersByStatus(String requestOrderStatus) {
        OrderStatus status = validator.validAndReturnStatus(requestOrderStatus);
//        var orders = ordersRepository.getOrdersByStatus(status);

        List<ResponseOrder> responseOrders = new ArrayList<>();
//        for (Order order : orders) {
//            responseOrders.add(mapOrderToResponseOrder(order));
//        }
        ResponseOrdersList respOrdersList = new ResponseOrdersList();
        respOrdersList.setOrders(responseOrders);
        return respOrdersList;
    }

    private ResponseMenuItem mapMenuItemToResponseMenuItem(RestaurantMenuItem restaurantMenuItem) {
        ResponseMenuItem respMenuItem = new ResponseMenuItem();
        respMenuItem.setId(restaurantMenuItem.getId());
        respMenuItem.setRestaurantId(restaurantMenuItem.getRestaurantId().getId());
        respMenuItem.setName(restaurantMenuItem.getName());
        respMenuItem.setPrice(restaurantMenuItem.getPrice() / MONEY_KOEFFICIENT);
        respMenuItem.setImage(restaurantMenuItem.getImage());
        respMenuItem.setDescription(restaurantMenuItem.getDescription());
        return respMenuItem;
    }

    private RestaurantMenuItem mapRequestMenuItemToMenuItem(RequestMenuItem requestMenuItem
            , RestaurantMenuItem restaurantMenuItem) {
        long restaurantId = requestMenuItem.getRestaurantId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        restaurantMenuItem.setRestaurantId(restaurant);
        restaurantMenuItem.setName(requestMenuItem.getName());
        restaurantMenuItem.setPrice(requestMenuItem.getPrice() * MONEY_KOEFFICIENT);
        restaurantMenuItem.setImage(requestMenuItem.getImage());
        restaurantMenuItem.setDescription(requestMenuItem.getDescription());
        return restaurantMenuItem;
    }

    private ResponseOrder mapOrderToResponseOrder(Order order) {
        ResponseRestaurantName respRestaurantName = new ResponseRestaurantName();
        respRestaurantName.setName(order.getRestaurantId().getName());

        ResponseOrder respOrder = new ResponseOrder();
        respOrder.setId(order.getId());
        respOrder.setRestaurant(respRestaurantName);
        respOrder.setTimestamp(order.getTimeStamp());
        respOrder.setItems(mapOrderItemToResponseOrderItem(order.getItems()));
        return respOrder;
    }

    private List<ResponseOrderItem> mapOrderItemToResponseOrderItem(
            List<OrderItem> orderItems) {

        List<ResponseOrderItem> respOrderItems = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            ResponseOrderItem responseOrderItem = new ResponseOrderItem();
            responseOrderItem.setPrice(orderItem.getPrice());
            responseOrderItem.setQuantity(orderItem.getQuantity());
            responseOrderItem.setDescription(orderItem.getRestaurantMenuItem()
                    .getDescription());
            responseOrderItem.setImage(orderItem.getRestaurantMenuItem()
                    .getImage());
            respOrderItems.add(responseOrderItem);
        }
        return respOrderItems;
    }
}