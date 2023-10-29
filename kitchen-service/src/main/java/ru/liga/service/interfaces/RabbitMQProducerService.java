package ru.liga.service.interfaces;

public interface RabbitMQProducerService {

    void sendMessage(String message, String routingKey);

}
