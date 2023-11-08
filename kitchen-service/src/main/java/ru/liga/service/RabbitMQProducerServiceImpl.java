package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
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
    public void pushNotificationDeliveryByNewOrder(String message, String routingKey) {
        rabbitTemplate.convertAndSend("notificationToDeliveryDirectExchange"
                , routingKey, message);
        log.info( message);
    }

}
