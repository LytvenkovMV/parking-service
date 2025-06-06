package ru.lytvenkovmv.parkingservice.dto.car;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class LeaveRequestDto {
    private UUID id;
    private LocalDateTime leaveTime;
}
