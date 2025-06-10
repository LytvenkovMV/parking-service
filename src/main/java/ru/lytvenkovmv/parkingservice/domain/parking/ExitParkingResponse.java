package ru.lytvenkovmv.parkingservice.domain.parking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExitParkingResponse {
    private String id;
    private String regNumber;
    private LocalDateTime exitTime;
}
