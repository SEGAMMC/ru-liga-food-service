package ru.liga.service;

public interface RabbitMQProducerService {

    void sendOrderToQueue(String message, String routingKey);

}
