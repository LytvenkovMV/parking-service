package ru.lytvenkovmv.parkingservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lytvenkovmv.parkingservice.dto.error.ErrorDto;
import ru.lytvenkovmv.parkingservice.exception.ParkingException;

@RestControllerAdvice
public class ExControllerAdvice {
    @ExceptionHandler(value = ParkingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto exceptionHandler(ParkingException ex) {
        return ErrorDto.builder()
                .type("Бизнес-ошибка")
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto exceptionHandler(MethodArgumentNotValidException ex) {
        return ErrorDto.builder()
                .type("Ошибка валидации тела запроса")
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto exceptionHandler(Exception ex) {
        return ErrorDto.builder()
                .type("Непредвиденная ошибка")
                .message(ex.getMessage())
                .build();
    }
}
