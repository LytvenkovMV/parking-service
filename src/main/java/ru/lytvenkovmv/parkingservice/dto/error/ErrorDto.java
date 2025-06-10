package ru.lytvenkovmv.parkingservice.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDto {
    @Schema(description = "Тип ошибки")
    private String type;

    @Schema(description = "Описание ошибки")
    private String message;
}
