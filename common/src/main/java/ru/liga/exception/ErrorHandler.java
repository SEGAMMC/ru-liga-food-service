package ru.liga.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final OrderNotFoundException e) {
        log.info("404", e.getMessage(), e);
        return new ApiError("404", "This order not found", LocalDateTime.now());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final CustomerNotFoundException e) {
        log.info("404", e.getMessage(), e);
        return new ApiError("404", "This customer not found", LocalDateTime.now());
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final MenuItemNotFoundException e) {
        log.info("404", e.getMessage(), e);
        return new ApiError("404", "This menu item not found", LocalDateTime.now());
    }

    @ExceptionHandler(RequestOrderStatusInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRequestInvalidException(final RequestOrderStatusInvalidException e) {
        log.info("400", e.getMessage(), e);
        return new ApiError("400", "Request Order Status invalid. ", LocalDateTime.now());
    }

    @ExceptionHandler(RequestOrderInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRequestOrderInvalidException(final RequestOrderInvalidException e) {
        log.info("400", e.getMessage(), e);
        return new ApiError("400", "Request Order information invalid. "+ e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(RequestMenuItemInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRequestMenuItemInvalidException(final RequestMenuItemInvalidException e) {
        log.info("400", e.getMessage(), e);
        return new ApiError("400", "Request Menu Item information invalid. "+ e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(CourierNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final CourierNotFoundException e) {
        log.info("404", e.getMessage(), e);
        return new ApiError("404", "This courier not found", LocalDateTime.now());
    }

    @ExceptionHandler(RequestPriceInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRequestPriceInvalidException(final RequestPriceInvalidException e) {
        log.info("400", e.getMessage(), e);
        return new ApiError("400", "Request price invalid. "+ e.getMessage(), LocalDateTime.now());
    }


	
}
