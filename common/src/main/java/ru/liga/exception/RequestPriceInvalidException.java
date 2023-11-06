package ru.liga.exception;

public class RequestPriceInvalidException extends RuntimeException {

    public RequestPriceInvalidException(double id) {
        super("Request price "+ id + " is negative. Price just positive price>0");
    }
}
