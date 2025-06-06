package ru.lytvenkovmv.parkingservice.service;

import ru.lytvenkovmv.parkingservice.dto.report.ReportDto;

import java.time.LocalDateTime;

public interface ReportService {
    ReportDto generate(LocalDateTime startDate, LocalDateTime endDate);
}
