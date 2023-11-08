package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.*;
import ru.liga.entity.Courier;
import ru.liga.enums.CourierStatus;
import ru.liga.enums.OrderStatus;
import ru.liga.exception.exceptions.CourierIllegalWorkException;
import ru.liga.exception.exceptions.CourierNotFoundException;
import ru.liga.exception.exceptions.RequestOrderStatusInvalidException;
import ru.liga.feign.FeignDelivery;
import ru.liga.repository.hibernate.CourierRepository;
import ru.liga.service.interfaces.DeliveryService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final FeignDelivery feignDelivery;
    private final CourierRepository courierRepository;
    private final GeoService geoService;
    private static final double CASH_BY_METTERS = 0.03;
    private static final double MAX_DISTANCE_FOR_COURIER = 20000; //20 km

    @Override
    public List<ResponseDeliveryOrder> getDeliveryOrdersByStatusPending(long id) {
        return getOrdersByStatus("DELIVERY_PENDING", id);
    }


    private List<ResponseDeliveryOrder> getOrdersByStatus(String requestStatus, long id) {
        List<ResponseDeliveryOrderForFindCourier> responseOrderList =
                feignDelivery.getOrdersByStatusDeliveryPending();

        Courier courier = courierRepository.findById(id).orElseThrow(() ->
                new CourierNotFoundException(id));
        if (courier.getStatus().equals(CourierStatus.INACTIVE) ||
                courier.getStatus().equals(CourierStatus.BUSY)) {
            throw new CourierIllegalWorkException(id);
        }
        List<ResponseDeliveryOrder> responseDeliveryOrderList = new ArrayList<>();
        for (ResponseDeliveryOrderForFindCourier respOrder : responseOrderList) {
            ResponseDeliveryOrder respDeliveryOrder =
                    mapOrderToResponseOrder(respOrder, courier);
            if (respDeliveryOrder.getSumDistance() < MAX_DISTANCE_FOR_COURIER) {
                responseDeliveryOrderList.add(respDeliveryOrder);
            }
        }
        log.info("[DeliveryServiceImpl:getOrdersByStatus]: " +
                        "Получили список заказов с соответствующим статусом {}"
                , requestStatus);
        return responseDeliveryOrderList;
    }

    @Override
    public void updateOrderStatusByDeliveryTake(String uuid) {
        ResponseOrderStatusByDelivery responseOrder = feignDelivery.getOrderByUuid(uuid);
        OrderStatus orderStatus = responseOrder.getOrderStatus();
        OrderStatus newStatus = null;
        if (orderStatus.equals(OrderStatus.DELIVERY_PENDING)) {
            newStatus = OrderStatus.DELIVERY_DELIVERING;
            RequestOrderStatus requestOrderStatus = new RequestOrderStatus();
            requestOrderStatus.setStatus(newStatus);
            requestOrderStatus.setUuid(uuid);
            feignDelivery.updateOrderStatusByDelivery(requestOrderStatus);
            // отправить сообщение клиенту о статусе

            log.info("[DeliveryServiceImpl:updateOrderStatusByDeliveryTake]: " +
                    "Обновили статус заказа id {} на {}", uuid, newStatus.toString());
        } else {
            throw new RequestOrderStatusInvalidException(orderStatus.toString());
        }
    }


    @Override
    public void updateOrderStatusByDeliveryComplete(String uuid) {
        ResponseOrderStatusByDelivery responseOrder = feignDelivery.getOrderByUuid(uuid);
        OrderStatus orderStatus = responseOrder.getOrderStatus();
        OrderStatus newStatus = null;

        if (orderStatus.equals(OrderStatus.DELIVERY_DELIVERING)) {
            newStatus = OrderStatus.DELIVERY_COMPLETE;
            RequestOrderStatus requestOrderStatus = new RequestOrderStatus();
            requestOrderStatus.setStatus(newStatus);
            requestOrderStatus.setUuid(uuid);
            feignDelivery.updateOrderStatusByDelivery(requestOrderStatus);
            // отправить сообщение клиенту о статусе

            log.info("[DeliveryServiceImpl:updateOrderStatusByDeliveryComplete]: " +
                    "Обновили статус заказа id {} на {}", uuid, newStatus.toString());
        } else {
            throw new RequestOrderStatusInvalidException(orderStatus.toString());
        }
    }

    private ResponseDeliveryOrder mapOrderToResponseOrder(ResponseDeliveryOrderForFindCourier order
            , Courier courier) {
        ResponseDeliveryOrder respDeliveryOrder = new ResponseDeliveryOrder();

        ResponseRestaurantDelivery respRestaurant = new ResponseRestaurantDelivery();
        String[] restAddress = order.getAddressRestaurant().split("\\|");
        String restaurantAddress = restAddress[0];
        respRestaurant.setAddress(restaurantAddress);

        ResponseCustomerDelivery respCustomer = new ResponseCustomerDelivery();
        String[] custAddress = order.getAddressCustomer().split("\\|");
        String customerAddress = custAddress[0];
        respCustomer.setAddress(customerAddress);

        double distanceCourierByRest = geoService
                .determineDistanceCourToRest(courier.getCoordinates()
                        , order.getAddressRestaurant());
        respRestaurant.setDistance(new DecimalFormat("#0")
                .format(distanceCourierByRest));

        double distanceRestByCustomer = geoService
                .determineDistanceRestToCustom(order.getAddressRestaurant()
                        , order.getAddressCustomer());
        respCustomer.setDistance(new DecimalFormat("#0")
                .format(distanceRestByCustomer));

        double paymentByDelivery =
                (distanceCourierByRest + distanceRestByCustomer)
                        * CASH_BY_METTERS;

        respDeliveryOrder.setUuid(order.getUuid());
        respDeliveryOrder.setRestaurant(respRestaurant);
        respDeliveryOrder.setCustomer(respCustomer);
        respDeliveryOrder.setPayment(new DecimalFormat("#0")
                .format(paymentByDelivery));
        respDeliveryOrder.setSumDistance(distanceCourierByRest + distanceRestByCustomer);

        return respDeliveryOrder;
    }
}
