package com.woven.assignment.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FileSizeException.class)
    protected ResponseEntity<Object> handleBadRequest(FileSizeException ex) {
        logger.error("Bad request- File size exceeded ", ex);
        return new ResponseEntity<Object>(ex.getErrorResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleBadRequest(Exception ex) {
        logger.error("Unhandled excpetion", ex);
        return new ResponseEntity<Object>(new ErrorResponse("UNHANDLED_EXCEPTION", "something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileNotFound.class)
    protected ResponseEntity<Object> handleBadRequest(FileNotFound ex) {
        logger.error("Unhandled excpetion", ex);
        return new ResponseEntity<Object>(ex.getErrorResponse(), HttpStatus.NOT_FOUND);
    }
}
