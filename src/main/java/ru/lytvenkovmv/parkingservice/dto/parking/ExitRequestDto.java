package ru.lytvenkovmv.parkingservice.dto.parking;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.lytvenkovmv.parkingservice.validation.ValidRegNumber;
import ru.lytvenkovmv.parkingservice.validation.ValidUUID;

@Getter
@Setter
public class ExitRequestDto {
    @Schema(description = "Идентификатор записи парковки (UUID)",
            example = "550e8400-e29b-41d4-a716-446655440000",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Идентификатор не может быть пустым")
    @ValidUUID
    private String id;

    @Schema(description = "Регистрационный номер транспортного средства",
            example = "A123BC150",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Регистрационный номер не может быть пустым")
    @ValidRegNumber
    private String regNumber;
}
