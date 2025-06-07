package ru.lytvenkovmv.parkingservice.dto.pageable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableDto {
    @NotNull(message = "Номер страницы не может быть пустым")
    @PositiveOrZero(message = "Номер страницы не может быть меньше 0")
    Integer page;

    @NotNull(message = "Размер страницы не может быть пустым")
    @Positive(message = "Размер страницы не может быть меньше или равен 0")
    Integer size;

    @NotBlank(message = "Название поля для сортировки не может быть пустой строкой")
    String sortBy;

    @Pattern(regexp = "asc|ASC|Asc|desc|DESK|Desk", message = "Направление сортировки может быть только asc или desk")
    String direction;
}
