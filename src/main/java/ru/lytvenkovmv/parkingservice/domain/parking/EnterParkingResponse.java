package ru.lytvenkovmv.parkingservice.domain.parking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnterParkingResponse {
    private String id;
    private String regNumber;
    private LocalDateTime enterTime;
}
