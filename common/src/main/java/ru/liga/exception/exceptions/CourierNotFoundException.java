package ru.liga.exception.exceptions;

public class CourierNotFoundException extends RuntimeException {

    public CourierNotFoundException(long id) {
        super("Courier id = "+ id + " is not found in DataBase");
    }

}
