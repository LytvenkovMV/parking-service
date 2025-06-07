package ru.lytvenkovmv.parkingservice.dto.parking;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import ru.lytvenkovmv.parkingservice.validation.ValidRegNumber;
import ru.lytvenkovmv.parkingservice.validation.ValidUUID;

import java.time.LocalDateTime;

@Getter
@Setter
public class LeaveRequestDto {
    @NotNull(message = "Идентификатор не может быть пустым")
    @ValidUUID
    private String id;

    @NotNull(message = "Регистрационный номер не может быть пустым")
    @ValidRegNumber
    private String regNumber;

    @NotNull(message = "Дата и время не могут быть пустыми")
    @PastOrPresent(message = "Дата и время не может быть больше текущих даты и времени")
    private LocalDateTime leaveTime;
}
