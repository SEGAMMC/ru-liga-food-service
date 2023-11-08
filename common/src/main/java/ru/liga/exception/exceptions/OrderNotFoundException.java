package ru.liga.exception.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(long id) {
        super("Order id = "+ id + " is not found in DataBase");
    }

    public OrderNotFoundException(String UUID) {
        super("Order id = "+ UUID + " is not found in DataBase");
    }
}
