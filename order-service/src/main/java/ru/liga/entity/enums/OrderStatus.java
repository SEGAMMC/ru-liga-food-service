package ru.liga.entity.enums;

public enum OrderStatus {
    AWAIT_PAY, //ожидает оплаты
    PAID_FOR, //оплачено
    ACCEPTED, //заказ принят в ресторане
    PREPARING, //заказ готовится
    ASSEMBLED, //заказ собран
    DELIVERY, //доставка заказа
    DELIVERED //доставлен
}
