package ru.liga.exception.exceptions;

import ru.liga.dto.request.RequestOrderStatus;

public class RequestOrderStatusInvalidException extends RuntimeException {

    public RequestOrderStatusInvalidException(String os) {
        super("This Order Status "+ os + " is not valid");
    }
}
