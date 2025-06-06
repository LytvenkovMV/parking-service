package ru.lytvenkovmv.parkingservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exceptionHandler(Exception ex) {
        return "Непредвиденная ошибка во время выполнения запроса" + ex.getMessage();
    }

}
