package ru.liga.exception.exceptions;

public class RequestCustomerInvalidException extends RuntimeException {

    public RequestCustomerInvalidException(long id) {
        super("Request customer id = "+ id + " is negative. Id just positive id>0");
    }
}