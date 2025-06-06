package ru.lytvenkovmv.parkingservice.dto.car;

import ru.lytvenkovmv.parkingservice.enums.VehicleType;

import java.time.LocalDateTime;

public class EnterVehicleRequestDto {
    private String regNumber;
    private VehicleType type;
    private LocalDateTime enterTime;
}
