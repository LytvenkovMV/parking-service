package ru.lytvenkovmv.parkingservice.dto.car;

import ru.lytvenkovmv.parkingservice.enums.VehicleType;

import java.time.LocalDateTime;
import java.util.UUID;

public class EnterVehicleResponseDto {
    private UUID id;
    private String regNumber;
    private VehicleType type;
    private LocalDateTime enterTime;
}
