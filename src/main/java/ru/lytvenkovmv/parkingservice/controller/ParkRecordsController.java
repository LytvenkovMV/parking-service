package ru.lytvenkovmv.parkingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.service.ParkRecordsService;
import ru.lytvenkovmv.parkingservice.validation.ValidUUID;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking/api/v1/records")
@Tag(name = "Парковка", description = "Операции для управления парковкой")
public class ParkRecordsController {
    private final ParkRecordsService parkRecordsService;

    @Operation(summary = "Получить все записи парковки",
            description = "Возвращает список записей парковки по пагинации")
    @ApiResponse(responseCode = "200",
            description = "Список записей парковки успешно получен")
    @PostMapping
    List<ParkRecordResponseDto> getAll(@RequestBody @Validated PageableDto pageableDto) {
        return parkRecordsService.findAll(pageableDto);
    }

    @Operation(summary = "Получить запись по ID",
            description = "Возвращает запись о парковке по идентификатору")
    @ApiResponse(responseCode = "200",
            description = "Запись найдена")
    @GetMapping("/{id}")
    ParkRecordResponseDto getById(@PathVariable @ValidUUID String id) {
        return parkRecordsService.findById(id);
    }
}
