package ru.lytvenkovmv.parkingservice.dto.parking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnterResponseDto {
    @Schema(description = "Идентификатор записи парковки")
    private String id;

    @Schema(description = "Регистрационный номер транспортного средства")
    private String regNumber;

    @Schema(description = "Время въезда на парковку")
    private LocalDateTime enterTime;
}
