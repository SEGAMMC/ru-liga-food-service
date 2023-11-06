package ru.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.entity.OrderModel;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final NotificationService notificationService;

    @SneakyThrows
    @RabbitListener(queues = "queueOrdersToKitchen")
    public void processMyQueue(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderModel messageModel = objectMapper.readValue(message, OrderModel.class);
        log.info("Order with id: " +  messageModel.getId()
                + " add in queue restaurantId: "+ messageModel.getId());
        notificationService.getOrders(messageModel);
    }

    @SneakyThrows
    @RabbitListener(queues = "queueOrdersToDelivery")
    public void processMyQueue2(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderModel messageModel = objectMapper.readValue(message, OrderModel.class);
        log.info("Order with id: " +  messageModel.getId()
                + " add in queue delivery: "+ messageModel.getId());
        notificationService.getOrders(messageModel);
    }

}
