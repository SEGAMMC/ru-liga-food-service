package ru.liga.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.liga.entity.Order;
import ru.liga.entity.OrderModel;
import ru.liga.service.interfaces.RabbitMQProducerService;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void pushNotificationCustomerByUpdateStatus(String message, String routingKey) {
        rabbitTemplate.convertAndSend("notificationToCustomerDirectExchange"
                , routingKey, message);
        log.info( message);
    }

    @Override
    public void pushNotificationKitchenByNewOrder(String message, String routingKey) {
        rabbitTemplate.convertAndSend("notificationToKitchenDirectExchange"
                , routingKey, message);
        log.info( message);
    }

    @Override
    public void pushNotificationKitchenByCancelOrder(String message, String routingKey) {
        rabbitTemplate.convertAndSend("notificationToCustomerDirectExchange"
                , routingKey, message);
        log.info( message);
    }





    @Override
    public void sendOrderToQueue(String message, String routingKey) {
        rabbitTemplate.convertAndSend("pushOrdersDirectExchange", routingKey, message);
        log.info("You has new order, this id" + message);
    }
}
