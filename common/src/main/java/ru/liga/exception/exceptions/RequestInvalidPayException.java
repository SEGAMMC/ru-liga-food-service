package ru.liga.exception.exceptions;

import ru.liga.dto.request.RequestPay;

public class RequestInvalidPayException extends RuntimeException {

    public RequestInvalidPayException(RequestPay requestPay) {
        super("Invalid request status order " + requestPay.getUuid());
    }

    public RequestInvalidPayException(RequestPay requestPay, String repeat) {
        super("Invalid request status order " + requestPay.getUuid() + ". Order alredy PAID.");
    }


    public RequestInvalidPayException(String UUID) {
        super("Invalid request status order " + UUID);
    }
}
