package ru.lytvenkovmv.parkingservice.dto.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDto {
    private String type;
    private String message;
}
