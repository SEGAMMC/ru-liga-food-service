package ru.liga.service;

public interface RabbitMQProducerService {

    void sendOrderToDelivery(String orderId);

}
