package ru.liga.exception.exceptions;

public class CourierIllegalWorkException extends RuntimeException {

    public CourierIllegalWorkException(long id) {
                super("Courier id = "+ id + " is not work at the moment");
    }

}
