package ru.lytvenkovmv.parkingservice.dto.pageable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableDto {
    @Schema(description = "Номер страницы (начинается с 0)",
            example = "0",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "0"
    )
    @NotNull(message = "Номер страницы не может быть пустым")
    @PositiveOrZero(message = "Номер страницы не может быть меньше 0")
    Integer page;

    @Schema(description = "Размер страницы",
            example = "50",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "1"
    )
    @NotNull(message = "Размер страницы не может быть пустым")
    @Positive(message = "Размер страницы не может быть меньше или равен 0")
    Integer size;

    @Schema(description = "Имя поля для сортировки",
            example = "regNumber"
    )
    String sortBy;

    @Schema(description = "Направление сортировки: asc (по возрастанию) или desk (по убыванию)",
            example = "asc",
            allowableValues = {"asc", "ASC", "Asc", "desc", "DESK", "Desk"}
    )
    @Pattern(regexp = "asc|ASC|Asc|desc|DESK|Desk", message = "Направление сортировки может быть только asc или desk")
    String direction;
}
