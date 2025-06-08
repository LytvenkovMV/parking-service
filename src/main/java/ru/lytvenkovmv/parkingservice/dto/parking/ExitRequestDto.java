package ru.lytvenkovmv.parkingservice.dto.parking;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.lytvenkovmv.parkingservice.validation.ValidRegNumber;

@Getter
@Setter
public class ExitRequestDto {
    @Schema(description = "Регистрационный номер транспортного средства",
            example = "A123BC150",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Регистрационный номер не может быть пустым")
    @ValidRegNumber
    private String regNumber;
}
