package ru.liga.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestOrder;
import ru.liga.dto.request.RequestOrderItem;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.request.RequestPay;
import ru.liga.dto.response.*;
import ru.liga.entity.*;
import ru.liga.enums.OrderStatus;
import ru.liga.exception.exceptions.*;
import ru.liga.repository.hibernate.*;
import ru.liga.service.interfaces.OrderService;
import ru.liga.service.interfaces.RabbitMQProducerService;
import ru.liga.util.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
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
    public ResponseOrderAccept createNewOrder(RequestOrder requestOrder) {
        validator.checkRequestOrder(requestOrder, requestOrder.getCustomerId());
        Order order = new Order();
        List<OrderItem> orderItemsList = new ArrayList<>();
        Customer customer = customerRepository.findById(requestOrder.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(requestOrder.getCustomerId()));
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

        String newUUID = UUID.randomUUID().toString();
        order.setUUID(newUUID);
        order.setItems(orderItemsList);
        order = ordersRepository.save(order);

        LocalDateTime timeCreate = order.getTimeStamp();
        LocalDateTime timeDelivery = timeCreate.plusMinutes(DEFAUL_TIME_DELIVERY);
        ResponseOrderAccept responseOrderAccept = new ResponseOrderAccept();
        responseOrderAccept.setSecretPaymentUrl(URL_PREFIX + newUUID);
        responseOrderAccept.setEstimatedTimeOfArrival(timeDelivery);
        responseOrderAccept.setId(order.getId());
        log.info("[OrderServiceImpl:createNewOrder]:" +
                " Создали новый заказ с id {}", order.getId());
        //todo отправить оповещение клиенту принят
        return responseOrderAccept;
    }

    @Override
    public void payOrder(RequestPay requestPay) {
        Order order = ordersRepository.findOrderByUUID(requestPay.getUuid());
        if (order == null) {
            throw new OrderNotFoundException(requestPay.getCustomerId());
        }
        if (order.getStatus().equals(OrderStatus.CUSTOMER_PAID)) {
            log.info("[OrderServiceImpl:payOrder]:" +
                    " Попытка оплатить уже оплаченный заказ {}", order.getUUID());
            throw new RequestInvalidPayException(requestPay, "repeat");
        }

        if (order.getStatus().equals(OrderStatus.CUSTOMER_CREATED)) {
            order.setStatus(OrderStatus.CUSTOMER_PAID);
            ordersRepository.save(order);
            sendOrderToQueue(order); //отправка заказа в очередь OrdersQueue
            log.info("[OrderServiceImpl:payOrder]:" +
                    " Оплатили заказ {}", order.getUUID());
            //todo отправить оповещение клиенту что оплачен
            //todo отправить оповещение на заказ на кухню
        } else throw new RequestInvalidPayException(requestPay);
    }


    @Override
    public void cancelOrderById(String UUID) {
        Order order = ordersRepository.findOrderByUUID(UUID);
        if (order == null) {
            throw new OrderNotFoundException(UUID);
        }

        if (order.getStatus().equals(OrderStatus.CUSTOMER_PAID)) {
            log.info("[OrderServiceImpl:payOrder]:" +
                    " Закрываем/отменяем уже оплаченный заказ {}", order.getUUID());
            //todo отправить оповещение клиенту об отмене
            //todo отправить оповещение на кухню об отмене

            order.setStatus(OrderStatus.CUSTOMER_CANCELLED);
            ordersRepository.save(order);
            refundMoneyById(UUID);
        } else if (order.getStatus().equals(OrderStatus.CUSTOMER_CREATED)) {
            log.info("[OrderServiceImpl:payOrder]:" +
                    " Закрываем/отменяем заказ {}", order.getUUID());
            //todo отправить оповещение клиенту об отмене
            //todo отправить оповещение на кухню об отмене
            order.setStatus(OrderStatus.CUSTOMER_CANCELLED);
            ordersRepository.save(order);
        } else throw new RequestInvalidPayException(UUID);
    }

    @Override
    public void updateOrderStatusByDelivery(RequestOrderStatus requestOrderStatus) {
        validator.isValidRequestStatus(requestOrderStatus);
        Order order = ordersRepository.findOrderByUUID(requestOrderStatus.getUuid());
        if (order == null) {
            throw new OrderNotFoundException(requestOrderStatus.getUuid());
        }
        if (order.getStatus().equals(OrderStatus.DELIVERY_DELIVERING)) {
            order.setStatus(requestOrderStatus.getStatus());
            order = ordersRepository.save(order);
        }
        if (order.getStatus().equals(OrderStatus.DELIVERY_PENDING)) {
            order.setStatus(requestOrderStatus.getStatus());
            order = ordersRepository.save(order);
        }


        log.info("[OrderServiceImpl:updateOrderStatusByDelivery]:" +
                " Изменили статус заказа с id {}, новый статус {}", order.getUUID(), order.getStatus());

        //TODO  сообщить клиенту o статусе

    }

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
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        var orders = ordersRepository.findByCustomerId(customer);
        List<ResponseOrder> responseOrders = new ArrayList<>();
        for (Order order : orders) {
            responseOrders.add(mapOrderToResponseOrder(order));
        }
        ResponseOrdersList respOrdersList = new ResponseOrdersList();
        respOrdersList.setOrders(responseOrders);
        log.info("[OrderServiceImpl:getOrdersByCustomerId]:" +
                " Получили заказы клиента с id {}", customer.getId());
        return respOrdersList;
    }


    @Override
    public ResponseOrder getOrderById(long orderId) {
        validator.isPositive(orderId);
        Order orderById = ordersRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        ResponseRestaurantName respRestaurantName = new ResponseRestaurantName();
        respRestaurantName.setName(orderById.getRestaurantId().getName());

        ResponseOrder respOrder = new ResponseOrder();
        respOrder.setId(orderById.getId());
        respOrder.setUuid(orderById.getUUID());
        respOrder.setRestaurant(respRestaurantName);
        respOrder.setTimestamp(orderById.getTimeStamp());
        respOrder.setItems(mapOrderItemToResponseOrderItem(orderById.getItems()));
        log.info("[OrderServiceImpl:getOrderById]:" +
                " Получили информацию о заказе с id {}", orderById.getId());
        return respOrder;
    }

    @Override
    public ResponseOrder getOrderByUuid(String uuid) {
        Order orderById = ordersRepository.findOrderByUUID(uuid);
        if (orderById == null) {
            throw new OrderNotFoundException(uuid);
        }
        ResponseRestaurantName respRestaurantName = new ResponseRestaurantName();
        respRestaurantName.setName(orderById.getRestaurantId().getName());

        ResponseOrder respOrder = new ResponseOrder();
        respOrder.setId(orderById.getId());
        respOrder.setUuid(orderById.getUUID());
        respOrder.setRestaurant(respRestaurantName);
        respOrder.setTimestamp(orderById.getTimeStamp());
        respOrder.setItems(mapOrderItemToResponseOrderItem(orderById.getItems()));
        log.info("[OrderServiceImpl:getOrderByUuid]:" +
                " Получили информацию о заказе с id {}", orderById.getUUID());
        return respOrder;
    }

    private void refundMoneyById(String UUID) {
        log.info("[OrderServiceImpl:refundMoneyById]:" +
                " Оформляяем возврат денежных средств по заказу id {}", UUID);

        //todo отправить оповещение клиенту
    }


    @Override
    public ResponseOrderStatusByKitchen getOrderByUuidByKitchen(String uuid) {
        Order orderById = ordersRepository.findOrderByUUID(uuid);
        if (orderById == null) {
            throw new OrderNotFoundException(uuid);
        }

        ResponseOrderStatusByKitchen respOrder = new ResponseOrderStatusByKitchen();
        respOrder.setOrderStatus(orderById.getStatus());

        log.info("[OrderServiceImpl:getOrderByUuidByKitchen]:" +
                " Получили информацию о заказе с id {}", orderById.getUUID());
        return respOrder;
    }

    @Override
    public ResponseOrderStatusByDelivery getOrderByUuidByDelivery(String uuid) {
        Order orderById = ordersRepository.findOrderByUUID(uuid);
        if (orderById == null) {
            throw new OrderNotFoundException(uuid);
        }

        ResponseOrderStatusByDelivery respOrder = new ResponseOrderStatusByDelivery();
        respOrder.setOrderStatus(orderById.getStatus());

        log.info("[OrderServiceImpl:getOrderByUuidByDelivery]:" +
                " Получили информацию о заказе с id {}", orderById.getUUID());
        return respOrder;
    }


    @Override
    public void updateOrderStatusByKitchen(RequestOrderStatus requestOrderStatus) {
        validator.isValidRequestStatus(requestOrderStatus);
        Order order = ordersRepository.findOrderByUUID(requestOrderStatus.getUuid());
        if (order == null) {
            throw new OrderNotFoundException(requestOrderStatus.getUuid());
        }
        order.setStatus(requestOrderStatus.getStatus());
        order = ordersRepository.save(order);

        if (order.getStatus().equals(OrderStatus.KITCHEN_DENIED)) {
            refundMoneyById(order.getUUID());
        }
        log.info("[OrderServiceImpl:updateOrderStatusByKitchen]:" +
                " Изменили статус заказа с id {}, новый статус {}", order.getUUID(), order.getStatus());
        //TODO  сообщить клиенту o статусе
    }

//////////////////////////////////////////////////////////////////////////////////


    @Override
    public void updateOrderStatus(RequestOrderStatus requestOrderStatus) {
        validator.isValidRequestStatus(requestOrderStatus);

        Order order = ordersRepository.findOrderByUUID(requestOrderStatus.getUuid());
        if (order == null) {
            throw new OrderNotFoundException(requestOrderStatus.getUuid());
        }


        order.setStatus(requestOrderStatus.getStatus());
        order = ordersRepository.save(order);
        log.info("[OrderServiceImpl:updateOrderStatus]:" +
                        " Изменили статус заказа с id {}, новый статус {}", order.getUUID()
                , order.getStatus().toString());
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
    public List<ResponseDeliveryOrderForFindCourier> getOrdersByStatusDelivery() {
        List<ResponseDeliveryOrderForFindCourier> responseOrderList = new ArrayList<>();
        var orders = ordersRepository.getOrdersByStatus(OrderStatus.DELIVERY_PENDING);

        for (Order order : orders) {
            ResponseDeliveryOrderForFindCourier respDeliveryOrder =
                    new ResponseDeliveryOrderForFindCourier();
            respDeliveryOrder.setUuid(order.getUUID());
            respDeliveryOrder.setAddressRestaurant(order.getRestaurantId().getAddress());
            respDeliveryOrder.setAddressCustomer(order.getCustomerId().getAddress());
            responseOrderList.add(respDeliveryOrder);
        }
        return responseOrderList;
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
        rabbitMQProducerService.sendOrderToQueue(newOrderToQueue, "order.new");
    }

}


