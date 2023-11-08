package ru.liga.exception.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(long id) {
        super("Restaurant id = "+ id + " is not found in DataBase");
    }
}
