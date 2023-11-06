package ru.liga.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestOrder;
import ru.liga.dto.request.RequestOrderItem;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.*;
import ru.liga.entity.*;
import ru.liga.enums.OrderStatus;
import ru.liga.exception.*;
import ru.liga.repository.hibernate.*;
import ru.liga.service.interfaces.OrderService;
import ru.liga.service.interfaces.RabbitMQProducerService;
import ru.liga.util.Validator;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final String URL_PREFIX = "http:/liga.ru/pay-";
    private static final long DEFAUL_TIME_DELIVERY = 90; //minut
    private static final double MONEY_KOEFFICIENT = 100d;// в БД price в копейках
    private final Validator validator;

    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMenuItemRepository restaurantMenuItemRepository;
    private final RabbitMQProducerService rabbitMQProducerService;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseOrdersList getOrders() {
        var orders = ordersRepository.findAll();
        List<ResponseOrder> responseOrders = new ArrayList<>();
        for (Order order : orders) {
            responseOrders.add(mapOrderToResponseOrder(order));
        }
        ResponseOrdersList respOrdersList = new ResponseOrdersList();
        respOrdersList.setOrders(responseOrders);
        return respOrdersList;
    }

    @Override
    public ResponseOrdersList getOrdersByCustomerId(long customerId) {
        validator.isPositive(customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));

        var orders = ordersRepository.findByCustomerId(customer);
        List<ResponseOrder> responseOrders = new ArrayList<>();
        for (Order order : orders) {
            responseOrders.add(mapOrderToResponseOrder(order));
        }
        ResponseOrdersList respOrdersList = new ResponseOrdersList();
        respOrdersList.setOrders(responseOrders);
        return respOrdersList;
    }

    @Override
    public void updateOrderStatus(RequestOrderStatus requestOrderStatus, long orderId) {
        validator.isPositive(orderId);
        validator.isValidRequestStatus(requestOrderStatus);

        Order orderById = ordersRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        orderById.setStatus(requestOrderStatus.getStatus());
        ordersRepository.save(orderById);
    }

    @Override
    public ResponseOrder getOrderById(long orderId) {
        validator.isPositive(orderId);
        Order orderById = ordersRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        ResponseRestaurantName respRestaurantName = new ResponseRestaurantName();
        respRestaurantName.setName(orderById.getRestaurantId().getName());

        ResponseOrder respOrder = new ResponseOrder();
        respOrder.setId(orderById.getId());
        respOrder.setRestaurant(respRestaurantName);
        respOrder.setTimestamp(orderById.getTimeStamp());

        respOrder.setItems(mapOrderItemToResponseOrderItem(orderById.getItems()));

        return respOrder;
    }

    @Override
    public ResponseOrderAccept createNewOrder(RequestOrder requestOrder, long customerId) {
        validator.checkRequestOrder(requestOrder, customerId);
        Order order = new Order();
        List<OrderItem> orderItemsList = new ArrayList<>();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        Restaurant restaurant = restaurantRepository.findById(requestOrder.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(requestOrder.getRestaurantId()));

        order.setCustomerId(customer);
        order.setRestaurantId(restaurant);
        order.setStatus(OrderStatus.CUSTOMER_CREATED);
        order.setTimeStamp(LocalDateTime.now());
        order = ordersRepository.save(order);

        List<RequestOrderItem> requestOrderItemList = requestOrder.getOrderItems();
        for (RequestOrderItem requestOrderItem : requestOrderItemList) {
            OrderItem orderItem = new OrderItem();
            RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository
                    .findById(requestOrderItem.getMenuItemId())
                    .orElseThrow(() -> new MenuItemNotFoundException(
                            requestOrderItem.getMenuItemId()));

            orderItem.setQuantity(requestOrderItem.getQuantity());
            orderItem.setRestaurantMenuItem(restaurantMenuItem);
            orderItem.setPrice(restaurantMenuItem.getPrice() * requestOrderItem.getQuantity());
            orderItem.setOrderId(order);
            orderItemRepository.save(orderItem);
            orderItemsList.add(orderItem);
        }
        order.setItems(orderItemsList);
        ordersRepository.save(order);

        sendOrderToQueue(order); //отправка заказа в очередь OrdersQueue

        LocalDateTime  timeCreate = order.getTimeStamp();
        LocalDateTime  timeDelivery = timeCreate.plusMinutes(DEFAUL_TIME_DELIVERY);
        ResponseOrderAccept responseOrderAccept = new ResponseOrderAccept();
        responseOrderAccept.setSecretPaymentUrl(URL_PREFIX + UUID.randomUUID());
        responseOrderAccept.setEstimatedTimeOfArrival(timeDelivery);
        responseOrderAccept.setId(order.getId());
        return responseOrderAccept;
    }

    @Override
    public ResponseOrdersList getOrdersByStatusCustomer(String status) {
        return getOrdersByStatus("CUSTOMER_" + status.toUpperCase());
    }

    @Override
    public ResponseOrdersList getOrdersByStatusKitchen(String status) {
        return getOrdersByStatus("KITCHEN_" + status.toUpperCase());
    }

    @Override
    public ResponseOrdersList getOrdersByStatusDelivery(String status) {
        return getOrdersByStatus("DELIVERY_" + status.toUpperCase());
    }

    private ResponseOrdersList getOrdersByStatus(String requestOrderStatus) {
        OrderStatus status = validator.validAndReturnStatus(requestOrderStatus);
        var orders = ordersRepository.getOrdersByStatus(status);
        List<ResponseOrder> responseOrders = new ArrayList<>();
        for (Order order : orders) {
            responseOrders.add(mapOrderToResponseOrder(order));
        }
        ResponseOrdersList respOrdersList = new ResponseOrdersList();
        respOrdersList.setOrders(responseOrders);
        return respOrdersList;
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

    private List<ResponseOrderItem> mapOrderItemToResponseOrderItem(List<OrderItem> orderItems) {

        List<ResponseOrderItem> respOrderItems = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            ResponseOrderItem responseOrderItem = new ResponseOrderItem();
            responseOrderItem.setPrice(orderItem.getPrice() / MONEY_KOEFFICIENT);
            responseOrderItem.setQuantity(orderItem.getQuantity());
            responseOrderItem.setDescription(orderItem.getRestaurantMenuItem().getDescription());
            responseOrderItem.setImage(orderItem.getRestaurantMenuItem().getImage());
            respOrderItems.add(responseOrderItem);
        }
        return respOrderItems;
    }

    private void sendOrderToQueue(Order order) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(order.getId());
        orderModel.setRestaurantId(order.getRestaurantId().getId());
        String newOrderToQueue = null;
        try {
            newOrderToQueue = objectMapper.writeValueAsString(orderModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        rabbitMQProducerService.sendOrderToQueue(newOrderToQueue, "restaurant");
    }

}


