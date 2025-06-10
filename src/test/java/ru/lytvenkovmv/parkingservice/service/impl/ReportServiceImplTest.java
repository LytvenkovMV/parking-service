package ru.lytvenkovmv.parkingservice.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lytvenkovmv.parkingservice.dto.report.ReportDto;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {
    @Mock
    private ParkRecordRepository repository;
    @InjectMocks
    private ReportServiceImpl service;

    @Test
    void when_generate_then_return_reportDto() {
        LocalDateTime startDate = LocalDateTime.of(2024, 6, 1, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 6, 30, 23, 59, 59);

        when(repository.countEntered(startDate, endDate)).thenReturn(15L);
        when(repository.countExited(startDate, endDate)).thenReturn(10L);
        when(repository.findAvgParkTimeInSeconds(startDate, endDate)).thenReturn(3600L);

        ReportDto report = service.generate(startDate, endDate);

        assertNotNull(report);
        assertEquals(15L, report.getEnteredVehiclesNumber());
        assertEquals(10L, report.getExitedVehiclesNumber());
        assertEquals(3600L, report.getAvgParkTimeInSeconds());
    }
}