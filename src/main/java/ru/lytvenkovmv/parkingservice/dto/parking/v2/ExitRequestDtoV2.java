package ru.lytvenkovmv.parkingservice.dto.parking.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.lytvenkovmv.parkingservice.validation.ValidRegNumber;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExitRequestDtoV2 {
    @Schema(description = "Регистрационный номер транспортного средства",
            example = "A123BC150",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Регистрационный номер не может быть пустым")
    @ValidRegNumber
    private String regNumber;

    @Schema(description = "Время выезда с парковки",
            example = "2020-01-01T00:00",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Время выезда с парковки не может быть пустым")
    private LocalDateTime exitTime;
}
