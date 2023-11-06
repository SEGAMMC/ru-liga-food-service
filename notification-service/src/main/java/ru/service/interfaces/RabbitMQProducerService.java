package ru.service.interfaces;

public interface RabbitMQProducerService {

    void resendOrderToKitchen(String message, String routingKey);

}
