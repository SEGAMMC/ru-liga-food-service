package ru.liga.exception.exceptions;

public class RequestPriceInvalidException extends RuntimeException {

    public RequestPriceInvalidException(double id) {
        super("Request price "+ id + " is negative. Price just positive price>0");
    }
}
