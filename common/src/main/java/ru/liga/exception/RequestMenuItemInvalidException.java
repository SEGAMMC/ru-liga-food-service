package ru.liga.exception;

public class RequestMenuItemInvalidException extends RuntimeException {

    public RequestMenuItemInvalidException(String errorsList) {
        super(errorsList);
    }
}
