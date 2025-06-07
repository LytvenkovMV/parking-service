package ru.lytvenkovmv.parkingservice.dto.parking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ParkRecordResponseDto {
    @Schema(description = "Идентификатор записи парковки")
    private UUID id;

    @Schema(description = "Регистрационный номер транспортного средства")
    private String regNumber;

    @Schema(description = "Тип транспортного средства")
    private String type;

    @Schema(description = "Время въезда на парковку")
    private LocalDateTime enterTime;

    @Schema(description = "Время выезда с парковки")
    private LocalDateTime leaveTime;
}
