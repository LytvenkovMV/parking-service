package ru.lytvenkovmv.parkingservice.dto.car;

import lombok.Getter;
import lombok.Setter;
import ru.lytvenkovmv.parkingservice.enums.VehicleType;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnterRequestDto {
    private String regNumber;
    private VehicleType type;
    private LocalDateTime enterTime;
}
