package ru.liga.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(long id) {
        super("Order id = "+ id + " is not found in DataBase");
    }
}
