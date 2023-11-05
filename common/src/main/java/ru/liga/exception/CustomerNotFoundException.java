package ru.liga.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(long id) {
        super("Customer id = "+ id + " is not found in DataBase");
    }
}
