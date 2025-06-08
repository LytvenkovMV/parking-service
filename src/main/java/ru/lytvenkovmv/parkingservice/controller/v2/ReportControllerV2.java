package ru.lytvenkovmv.parkingservice.controller.v2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.parkingservice.dto.report.v2.ReportDtoV2;
import ru.lytvenkovmv.parkingservice.service.ReportService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking/api/v2/reports")
@Tag(name = "Отчёты", description = "Генерация отчётов по записям парковки")
public class ReportControllerV2 {
    private final ReportService<ReportDtoV2> reportService;

    @Operation(summary = "Генерация отчёта по парковке",
            description = "Создаёт отчёт по записям парковки за выбранный период времени")
    @ApiResponse(responseCode = "200",
            description = "Отчёт успешно создан")
    @GetMapping()
    ReportDtoV2 generate(@Parameter(description = "Дата и время начала интервала", example = "2024-06-01T00:00:00")
                         @RequestParam LocalDateTime startDate,
                         @Parameter(description = "Дата и время конца интервала", example = "2024-06-30T23:59:59")
                         @RequestParam LocalDateTime endDate) {

        return reportService.generate(startDate, endDate);
    }
}
