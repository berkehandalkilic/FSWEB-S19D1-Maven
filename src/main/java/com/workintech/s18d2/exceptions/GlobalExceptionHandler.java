package com.workintech.s18d2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlantException.class)
    public ResponseEntity<ErrorDetail> handleFruitException(PlantException ex){
        ErrorDetail detail = new ErrorDetail(ex.getMessage());
        return new ResponseEntity<>(detail, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception ex){
        ErrorDetail detail = new ErrorDetail(ex.getMessage());
        return new ResponseEntity<>(detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
