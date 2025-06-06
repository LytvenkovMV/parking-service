package ru.lytvenkovmv.parkingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.parkingservice.dto.report.ReportDto;
import ru.lytvenkovmv.parkingservice.service.ReportService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking/api/v1/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping()
    ReportDto generate(@RequestParam(required = false) LocalDateTime startDate, @RequestParam(required = false) LocalDateTime endDate) {

        return reportService.generate(startDate, endDate);
    }
}
