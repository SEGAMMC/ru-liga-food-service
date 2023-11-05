package ru.liga.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.liga.dto.request.*;
import ru.liga.entity.Restaurant;
import ru.liga.enums.OrderStatus;
import ru.liga.exception.RequestCustomerInvalidException;
import ru.liga.exception.RequestMenuItemInvalidException;
import ru.liga.exception.RequestOrderInvalidException;
import ru.liga.exception.RequestOrderStatusInvalidException;
import ru.liga.repository.hibernate.CustomerRepository;
import ru.liga.repository.hibernate.OrdersRepository;
import ru.liga.repository.hibernate.RestaurantMenuItemRepository;
import ru.liga.repository.hibernate.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
@Component
public class Validator {
    private final OrdersRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantMenuItemRepository menuItemRepository;

    public void checkRequestOrder(RequestOrder requestOrder, long customerId) {
        List<String> errorList = new ArrayList<>();
        boolean isValid = true;
        long requestRestaurantId = requestOrder.getRestaurantId();
        if (requestRestaurantId <= 0) {
            errorList.add("Restaurant id: " + requestRestaurantId + " no valid. Restaurant id<=0.");
            isValid = false;
        } else {
            if (!restaurantRepository.existsById(requestRestaurantId)) {
                errorList.add("Restaurant with id: " + requestRestaurantId + " not found.");
                isValid = false;
            }
        }

        if (customerId <= 0) {
            errorList.add("Customer id: " + customerId + " no valid. Customer id<=0.");
            isValid = false;
        } else {
            if (!customerRepository.existsById(customerId)) {
                errorList.add("Customer with id: " + customerId + " not found.");
                isValid = false;
            }
        }

        List<RequestOrderItem> requestOrderItemList = requestOrder.getOrderItems();
        for (RequestOrderItem orderItems : requestOrderItemList) {
            int quantity = orderItems.getQuantity();
            if (quantity <= 0) {
                errorList.add("Quantity " + quantity + " no valid. Quantity id<=0.");
                isValid = false;
            }

            long menuItemId = orderItems.getMenuItemId();
            if (menuItemId <= 0) {
                errorList.add("Item by menu id " + menuItemId + " no valid. Item id<=0.");
                isValid = false;
            } else {
                if (menuItemRepository.existsById(menuItemId)) {
                    long restaurantId = menuItemRepository.findById(menuItemId).get()
                            .getRestaurantId().getId();
                    if (restaurantId != requestRestaurantId) {
                        errorList.add("This MenuItem with id: " + menuItemId + " not found in restaurant with id: " + requestRestaurantId);
                        isValid = false;
                    }
                } else {
                    errorList.add("MenuItem with id: " + menuItemId + " not found.");
                    isValid = false;
                }

            }
            if (!isValid) {
                throw new RequestOrderInvalidException(errorList.toString());
            }
        }
    }

    public boolean isValidRequestStatus(RequestOrderStatus requestOrderStatus) {
        boolean validRequestStatus = Stream.of(OrderStatus.values())
                .anyMatch(v -> v.name().equals(requestOrderStatus));
        if (validRequestStatus) {
            throw new RequestOrderStatusInvalidException(requestOrderStatus.toString());
        }
        return validRequestStatus;
    }

    public OrderStatus validAndReturnStatus(String requestOrderStatus) {
        OrderStatus currentStatus = null;
        for (OrderStatus status : OrderStatus.values()) {
            if (status.toString().equals(requestOrderStatus)) {
                currentStatus = status;
            }
        }
        if (currentStatus == null) {
            throw new RequestOrderStatusInvalidException(requestOrderStatus);
        }
        return currentStatus;
    }

    public boolean isPositive(long id) {
        if (id <= 0) {
            throw new RequestCustomerInvalidException(id);
        }
        return true;

    }

    public void checkRequestMenuItem(RequestMenuItem requestMenuItem) {
        List<String> errorList = new ArrayList<>();
        boolean isValid = true;
        long requestRestaurantId = requestMenuItem.getRestaurantId();
        if (requestRestaurantId <= 0) {
            errorList.add("Restaurant id: " + requestRestaurantId + " no valid. Restaurant id<=0.");
            isValid = false;
        }

        double requestPrice = requestMenuItem.getPrice();
        if (requestPrice <= 0) {
            errorList.add("Price " + requestPrice + " no valid. Price<=0.");
            isValid = false;
        }

        if (!isValid) {
            throw new RequestMenuItemInvalidException(errorList.toString());
        }

    }

}