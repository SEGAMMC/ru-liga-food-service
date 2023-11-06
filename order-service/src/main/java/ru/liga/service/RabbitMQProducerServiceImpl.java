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
    public void sendOrderToQueue(String message, String routingKey) {
        rabbitTemplate.convertAndSend("pushOrdersDirectExchange", routingKey,message);
        log.info("You has new order, this id" + message);
    }
}
