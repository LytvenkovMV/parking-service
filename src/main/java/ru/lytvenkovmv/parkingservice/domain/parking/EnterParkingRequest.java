package ru.lytvenkovmv.parkingservice.domain.parking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnterParkingRequest {
    private String regNumber;
    private String type;
    private LocalDateTime enterTime;
}
