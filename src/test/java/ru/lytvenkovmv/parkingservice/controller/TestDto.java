package ru.lytvenkovmv.parkingservice.controller;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestDto {
    @NotNull
    private String id;
}
