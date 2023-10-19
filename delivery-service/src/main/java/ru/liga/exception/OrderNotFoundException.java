package ru.liga.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("Order id = "+ id + "is not found");
    }
}
