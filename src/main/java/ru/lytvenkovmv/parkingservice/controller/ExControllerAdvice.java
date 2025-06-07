package ru.lytvenkovmv.parkingservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lytvenkovmv.parkingservice.dto.error.ErrorDto;
import ru.lytvenkovmv.parkingservice.exception.ParkingException;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ExceptionHandler(value = ParkingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto exceptionHandler(ParkingException ex) {
        log.warn("Бизнес-ошибка {}", ex.getMessage());

        return ErrorDto.builder()
                .type("Бизнес-ошибка")
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto exceptionHandler(MethodArgumentNotValidException ex) {
        log.warn("Ошибка валидации тела запроса {}", ex.getMessage());

        return ErrorDto.builder()
                .type("Ошибка валидации тела запроса")
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto exceptionHandler(Exception ex) {
        log.error("Непредвиденная ошибка {}", ex.getMessage(), ex);

        return ErrorDto.builder()
                .type("Непредвиденная ошибка")
                .message(ex.getMessage())
                .build();
    }
}
