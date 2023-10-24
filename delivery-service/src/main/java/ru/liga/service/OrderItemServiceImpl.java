package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestNewOrderItem;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.dto.response.ResponseOrderItem;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.repository.hibernate.OrderItemRepository;
import ru.liga.repository.hibernate.OrdersRepository;
import ru.liga.repository.mybatis.OrderItemRepositoryBatis;
import ru.liga.service.interfaces.OrderItemService;


@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepositoryBatis orderItemRepositoryBatis;
    private final OrderItemRepository orderItemRepository;
    private final OrdersRepository ordersRepository;

    @Override
    public ResponseOrderItem getOrderItemById(long id) {
        OrderItem orderItem = orderItemRepositoryBatis.getOrderItemById(id);
        ResponseOrderItem responseOrderItem = new ResponseOrderItem();
        responseOrderItem.setId(orderItem.getId());
        responseOrderItem.setOrderId(orderItem.getOrderId());
        responseOrderItem.setRestaurantMenuItem(orderItem.getRestaurantMenuItem());
        responseOrderItem.setPrice(orderItem.getPrice());
        responseOrderItem.setQuantity(orderItem.getQuantity());
        return responseOrderItem;
    }

    @Override
    public ResponseMenuItem getMenuItem(long id) {
        OrderItem orderItem = orderItemRepositoryBatis.getOrderItemById(id);
        RestaurantMenuItem menuItem = orderItem.getRestaurantMenuItem();
        ResponseMenuItem responseMenuItem = new ResponseMenuItem();
        responseMenuItem.setId(menuItem.getId());
        responseMenuItem.setRestaurantId(menuItem.getRestaurantId());
        responseMenuItem.setDescription(menuItem.getDescription());
        responseMenuItem.setImage(menuItem.getImage());
        responseMenuItem.setName(menuItem.getName());
        return responseMenuItem;
    }

    @Override
    public ResponseOrderItem createNewOrderItem(RequestNewOrderItem requestOrderItem) {
        Order  order = ordersRepository.findById(1l).get();

        System.out.println(order);
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOrderId(requestOrderItem.getOrderId());
//        orderItem.setPrice(requestOrderItem.getPrice());
//        orderItem.setQuantity(requestOrderItem.getQuantity());
//        orderItem.setRestaurantMenuItem(requestOrderItem.getRestaurantMenuItem());
//        List<OrderItem> orderItemList = order.getOrderItems();
//        orderItemList.add(orderItem);
//        order.setOrderItems(orderItemList);
//        orderItemRepository.save(orderItem);
//        ordersRepository.save(order);
//

        ResponseOrderItem responseOrderItem = new ResponseOrderItem();
//        responseOrderItem.setOrderId(orderItem.getOrderId());
//        responseOrderItem.setPrice(orderItem.getPrice());
//        responseOrderItem.setQuantity(orderItem.getQuantity());
//        responseOrderItem.setRestaurantMenuItem(orderItem.getRestaurantMenuItem());
//        responseOrderItem.setId(orderItem.getId());
        return responseOrderItem;
    }

}
