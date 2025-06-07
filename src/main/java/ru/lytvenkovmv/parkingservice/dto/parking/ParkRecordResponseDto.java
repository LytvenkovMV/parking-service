package ru.lytvenkovmv.parkingservice.dto.parking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ParkRecordResponseDto {
    UUID id;
    String regNumber;
    String type;
    LocalDateTime enterTime;
    LocalDateTime leaveTime;
}
