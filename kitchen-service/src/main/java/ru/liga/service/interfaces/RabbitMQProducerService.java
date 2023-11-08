package ru.liga.service.interfaces;

public interface RabbitMQProducerService {

    void sendOrderToDelivery(String orderId);

}
