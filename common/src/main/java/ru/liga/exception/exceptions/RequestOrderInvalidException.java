package ru.liga.exception.exceptions;

public class RequestOrderInvalidException extends RuntimeException {

    public RequestOrderInvalidException(String errorsList) {
        super(errorsList);
    }
}
