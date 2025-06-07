package ru.lytvenkovmv.parkingservice.dto.parking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnterResponseDto {
    private String id;
    private String regNumber;
    private String type;
    private LocalDateTime enterTime;
}
