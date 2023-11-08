package ru.liga.exception.exceptions;

public class RequestMenuItemInvalidException extends RuntimeException {

    public RequestMenuItemInvalidException(String errorsList) {
        super(errorsList);
    }
}
