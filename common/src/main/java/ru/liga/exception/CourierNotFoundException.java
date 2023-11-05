package ru.liga.exception;

public class CourierNotFoundException extends RuntimeException {

    public CourierNotFoundException(long id) {
        super("Courier id = "+ id + " is not found in DataBase");
    }

    public CourierNotFoundException(String phone) {
        super("Courier with phone = "+ phone + " is not found in DataBase");
    }
}
