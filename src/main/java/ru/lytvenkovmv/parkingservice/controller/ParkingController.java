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
import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.service.ParkingService;
import ru.lytvenkovmv.parkingservice.validation.ValidUUID;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking/api/v1")
@Tag(name = "Парковка", description = "Операции для управления парковкой")
public class ParkingController {
    private final ParkingService parkingService;

    @Operation(summary = "Получить все записи парковки",
            description = "Возвращает список записей парковки по пагинации")
    @ApiResponse(responseCode = "200",
            description = "Список записей парковки успешно получен")
    @PostMapping("/records")
    List<ParkRecordResponseDto> getAll(@RequestBody @Validated PageableDto pageableDto) {
        return parkingService.findAll(pageableDto);
    }

    @Operation(summary = "Получить запись по ID",
            description = "Возвращает запись о парковке по идентификатору")
    @ApiResponse(responseCode = "200",
            description = "Запись найдена")
    @GetMapping("/records/{id}")
    ParkRecordResponseDto getById(@PathVariable @ValidUUID String id) {
        return parkingService.findById(id);
    }

    @Operation(summary = "Въезд на парковку",
            description = "Добавление информации о въезде")
    @ApiResponse(responseCode = "200",
            description = "Въезд успешно зарегистрирован")
    @PostMapping("/entry")
    EnterResponseDto entry(@RequestBody @Validated EnterRequestDto requestDto) {
        return parkingService.enterParking(requestDto);
    }

    @Operation(summary = "Выезд с парковки",
            description = "Добавление информации о выезде")
    @ApiResponse(responseCode = "200",
            description = "Выезд успешно зарегистрирован")
    @PostMapping("/exit")
    ExitResponseDto exit(@RequestBody @Validated ExitRequestDto requestDto) {
        return parkingService.exitParking(requestDto);
    }
}
