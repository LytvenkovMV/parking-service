package ru.lytvenkovmv.parkingservice.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.parkingservice.exception.ParkingException;

@RestController
class TestController {
    @GetMapping("/parking-ex")
    public void throwParkingException() {
        throw new ParkingException("К сожалению нет свободных мест");
    }

    @PostMapping("/validation-ex")
    public void validateDto(@Valid @RequestBody TestDto dto) {
    }

    @GetMapping("/other-ex")
    public void throwException() {
        throw new RuntimeException("Что-то пошло не так");
    }
}