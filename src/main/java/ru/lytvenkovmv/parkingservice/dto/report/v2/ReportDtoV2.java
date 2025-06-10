package ru.lytvenkovmv.parkingservice.dto.report.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReportDtoV2 {
    @Schema(description = "Общее количество т/с, въезжавших на парковку за указанный период")
    private Long enteredVehiclesNumber;

    @Schema(description = "Общее количество т/с, выезжавших с парковки за указанный период")
    private Long exitedVehiclesNumber;

    @Schema(description = "Количество свободных мест на текущий момент времени")
    private Integer availablePlaces;

    @Schema(description = "Количество занятых мест на текущий момент времени")
    private Integer occupiedPlaces;

    @Schema(description = "Среднее время нахождения т/с на парковке в секундах за указанный период")
    private Long avgParkTimeInSeconds;
}
