package ru.lytvenkovmv.parkingservice.dto.parking;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import ru.lytvenkovmv.parkingservice.validation.ValidRegNumber;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnterRequestDto {
    @NotNull(message = "Регистрационный номер не может быть пустым")
    @ValidRegNumber
    private String regNumber;

    @Pattern(regexp = "car|truck|bus|motorcycle|tractor|other|CAR|TRUCK|BUS|MOTORCYCLE|TRACTOR|OTHER|Car|Truck|Bus|Motorcycle|Tractor|Other",
            message = "Неверно указан тип т/с. Допустимые типы: car, truck, bus, motorcycle, tractor, other")
    private String type;

    @NotNull(message = "Дата и время не могут быть пустыми")
    @PastOrPresent(message = "Дата и время не может быть больше текущих даты и времени")
    private LocalDateTime enterTime;
}
