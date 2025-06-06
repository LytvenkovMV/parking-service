package ru.lytvenkovmv.parkingservice.dto.car;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class LeaveResponseDto {
    private UUID id;
    private String regNumber;
    private LocalDateTime leaveTime;
}
