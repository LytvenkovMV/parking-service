package ru.lytvenkovmv.parkingservice.dto.car;

import lombok.Getter;
import lombok.Setter;
import ru.lytvenkovmv.parkingservice.enums.VehicleType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class EnterResponseDto {
    private UUID id;
    private String regNumber;
    private VehicleType type;
    private LocalDateTime enterTime;
}
