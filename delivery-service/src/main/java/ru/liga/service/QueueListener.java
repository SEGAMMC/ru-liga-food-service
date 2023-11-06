package ru.liga.service;

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

    @SneakyThrows
    @RabbitListener(queues = "queueOrdersToDelivery")
    public void processMyQueue(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderModel messageModel = objectMapper.readValue(message, OrderModel.class);
        log.info("Received from queueOrders : " +  messageModel.getId()+"  " + messageModel.getRestaurantId());
        System.out.println("Received from queueOrders : " +  messageModel.getId()+"  " + messageModel.getRestaurantId());
    }

    @SneakyThrows
    @RabbitListener(queues = "queueOrdersToCouriers")
    public void processMyQueue2(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderModel messageModel = objectMapper.readValue(message, OrderModel.class);
        log.info("Received from queueOrders : " +  messageModel.getId()+"  " + messageModel.getRestaurantId());
        System.out.println("Received from queueOrders : " +  messageModel.getId()+"  " + messageModel.getRestaurantId());
    }

}
