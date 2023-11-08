package ru.liga.service.interfaces;


public interface RabbitMQProducerService {

    void pushNotificationCustomerByUpdateStatus(String message, String routingKey);

    void sendOrderToQueue(String message, String routingKey);

    void pushNotificationKitchenByNewOrder(String message, String routingKey);

    void pushNotificationKitchenByCancelOrder(String uuid, String routingKey);
}


