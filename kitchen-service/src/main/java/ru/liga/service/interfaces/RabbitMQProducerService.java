package ru.liga.service.interfaces;


public interface RabbitMQProducerService {

    void pushNotificationCustomerByUpdateStatus(String message, String routingKey);

    void pushNotificationDeliveryByNewOrder(String message, String routingKey);

}


