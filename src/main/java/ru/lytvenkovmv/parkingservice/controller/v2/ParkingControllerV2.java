package ru.lytvenkovmv.parkingservice.controller.v2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.v2.EnterRequestDtoV2;
import ru.lytvenkovmv.parkingservice.dto.parking.v2.ExitRequestDtoV2;
import ru.lytvenkovmv.parkingservice.service.ParkingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking/api/v2")
@Tag(name = "Парковка", description = "Операции для управления парковкой")
public class ParkingControllerV2 {
    private final ParkingService<EnterRequestDtoV2, ExitRequestDtoV2, EnterResponseDto, ExitResponseDto> parkingServiceV2;

    @Operation(summary = "Въезд на парковку",
            description = "Добавление информации о въезде")
    @ApiResponse(responseCode = "200",
            description = "Въезд успешно зарегистрирован")
    @PostMapping("/entry")
    EnterResponseDto entry(@RequestBody @Validated EnterRequestDtoV2 requestDto) {
        return parkingServiceV2.enterParking(requestDto);
    }

    @Operation(summary = "Выезд с парковки",
            description = "Добавление информации о выезде")
    @ApiResponse(responseCode = "200",
            description = "Выезд успешно зарегистрирован")
    @PostMapping("/exit")
    ExitResponseDto exit(@RequestBody @Validated ExitRequestDtoV2 requestDto) {
        return parkingServiceV2.exitParking(requestDto);
    }
}
