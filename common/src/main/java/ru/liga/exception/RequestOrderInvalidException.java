package ru.liga.exception;

public class RequestOrderInvalidException extends RuntimeException {

    public RequestOrderInvalidException(String errorsList) {
        super(errorsList);
    }
}
