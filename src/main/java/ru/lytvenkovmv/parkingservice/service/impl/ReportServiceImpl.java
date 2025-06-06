package ru.lytvenkovmv.parkingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.parkingservice.dto.report.ReportDto;
import ru.lytvenkovmv.parkingservice.service.ReportService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    @Override
    public ReportDto generate(LocalDateTime startDate, LocalDateTime endDate) {


        return null;
    }
}
