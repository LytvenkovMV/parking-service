package ru.lytvenkovmv.parkingservice.dto.parking;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import ru.lytvenkovmv.parkingservice.validation.ValidRegNumber;

@Getter
@Setter
public class EnterRequestDto {
    @Schema(description = "Регистрационный номер транспортного средства",
            example = "A123BC150",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Регистрационный номер не может быть пустым")
    @ValidRegNumber
    private String regNumber;

    @Schema(description = "Тип транспортного средства. car, truck, bus, motorcycle, tractor, other",
            example = "car",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Тип транспортного средства не может быть пустым")
    @Pattern(regexp = "car|truck|bus|motorcycle|tractor|other|CAR|TRUCK|BUS|MOTORCYCLE|TRACTOR|OTHER|Car|Truck|Bus|Motorcycle|Tractor|Other",
            message = "Неверно указан тип т/с. Допустимые типы: car, truck, bus, motorcycle, tractor, other")
    private String type;
}
