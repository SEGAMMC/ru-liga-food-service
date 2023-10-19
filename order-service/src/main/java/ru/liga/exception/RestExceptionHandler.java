package ru.liga.exception;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<?> handleOrderNotFoundEx(OrderNotFoundException ex) {
        ApiError apiError = new ApiError("Order Not Found Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
