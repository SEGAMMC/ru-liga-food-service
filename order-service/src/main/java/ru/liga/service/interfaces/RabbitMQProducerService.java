package ru.liga.service.interfaces;

public interface RabbitMQProducerService {

    void sendOrderToQueue(String message, String routingKey);

}
