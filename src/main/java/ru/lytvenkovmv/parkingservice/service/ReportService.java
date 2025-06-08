package ru.lytvenkovmv.parkingservice.service;

import java.time.LocalDateTime;

public interface ReportService<R> {
    R generate(LocalDateTime startDate, LocalDateTime endDate);
}
