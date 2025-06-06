package ru.lytvenkovmv.parkingservice.dto.car;

import java.time.LocalDateTime;
import java.util.UUID;

public class LeaveVehicleResponseDto {
    private UUID id;
    private String regNumber;
    private LocalDateTime leaveTime;
}
