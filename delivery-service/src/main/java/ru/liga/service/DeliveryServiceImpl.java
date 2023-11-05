package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.ResponseCustomerDelivery;
import ru.liga.dto.response.ResponseDeliveryOrder;
import ru.liga.dto.response.ResponseRestaurantDelivery;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;
import ru.liga.enums.OrderStatus;
import ru.liga.exception.OrderNotFoundException;
import ru.liga.feign.FeignDelivery;
import ru.liga.repository.hibernate.OrdersRepository;
import ru.liga.service.interfaces.DeliveryService;
import ru.liga.util.Validator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final OrdersRepository ordersRepository;
    private final FeignDelivery feignDelivery;
    private final Validator validator;
    private final GeoService geoService;
    private static final double CASH_BY_METTERS = 0.3;

    @Override
    public List<ResponseDeliveryOrder> getOrdersByStatusDelivery(
            String requestStatus) {
        return getOrdersByStatus("DELIVERY_" + requestStatus.toUpperCase());
    }

    private List<ResponseDeliveryOrder> getOrdersByStatus(
            String requestStatus) {
        OrderStatus status = validator.validAndReturnStatus(requestStatus);

        List<Order> orders = ordersRepository.getOrdersByStatus(status);
        List<ResponseDeliveryOrder> responseDeliveryOrderList = new ArrayList<>();
        for (Order order : orders) {
            ResponseDeliveryOrder respDeliveryOrder = convertOrderToResponseOrder(order);
            responseDeliveryOrderList.add(respDeliveryOrder);
        }
        return responseDeliveryOrderList;
    }

    @Override
    public ResponseDeliveryOrder getOrderByIdDelivery(long id) {
        validator.isPositive(id);
        Order order = ordersRepository.findById(id).orElseThrow(()->new OrderNotFoundException(id));
        return convertOrderToResponseOrder(order);
    }

    @Override
    public void updateDeliveryOrderStatus(RequestOrderStatus requestDeliveryStatus, long id) {
        validator.isPositive(id);

        feignDelivery.updateOrderStatus(requestDeliveryStatus, id);
    }

    private ResponseDeliveryOrder convertOrderToResponseOrder(Order order) {
        Customer customerOrder = order.getCustomerId();
        Restaurant restaurantOrder = order.getRestaurantId();
        Courier courierOrder = order.getCourierId();

        ResponseDeliveryOrder respDeliveryOrder = new ResponseDeliveryOrder();

        ResponseRestaurantDelivery respRestaurant = new ResponseRestaurantDelivery();
        String[] restAddress =restaurantOrder.getAddress().split("\\|");
        String restaurantAddress =restAddress[0];
        respRestaurant.setAddress(restaurantAddress);

        ResponseCustomerDelivery respCustomer = new ResponseCustomerDelivery();
        String[] custAddress =customerOrder.getAddress().split("\\|");
        String customerAddress =custAddress[0];
        respCustomer.setAddress(customerAddress);

        double distanceCourierByRestaurant = geoService.determineDistance(courierOrder, restaurantOrder);
        respRestaurant.setDistance(new DecimalFormat("#0").format(distanceCourierByRestaurant));

        double distanceRestaurantByCustomer = geoService.determineDistance(restaurantOrder, customerOrder);
        respCustomer.setDistance(new DecimalFormat("#0").format(distanceRestaurantByCustomer));

        double paymentByDelivery = (distanceCourierByRestaurant + distanceRestaurantByCustomer) * CASH_BY_METTERS;

        respDeliveryOrder.setOrderId(order.getId());
        respDeliveryOrder.setRestaurant(respRestaurant);
        respDeliveryOrder.setCustomer(respCustomer);
        respDeliveryOrder.setPayment(new DecimalFormat("#0").format(paymentByDelivery));

        return respDeliveryOrder;
    }

}
