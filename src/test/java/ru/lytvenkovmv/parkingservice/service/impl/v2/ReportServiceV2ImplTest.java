package ru.lytvenkovmv.parkingservice.service.impl.v2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lytvenkovmv.parkingservice.dto.report.v2.ReportDtoV2;
import ru.lytvenkovmv.parkingservice.properties.ParkingProperties;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;
import ru.lytvenkovmv.parkingservice.util.ParkingUtil;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceV2ImplTest {
    @Mock
    private ParkRecordRepository repository;
    @Mock
    private ParkingProperties parkingProperties;
    @Mock
    private ParkingUtil parkingUtil;
    @InjectMocks
    private ReportServiceV2Impl service;

    @Test
    void when_generate_then_return_reportDto() {
        LocalDateTime startDate = LocalDateTime.of(2024, 6, 1, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 6, 30, 23, 59, 59);

        when(parkingUtil.calcAvailablePlaces()).thenReturn(7);
        when(parkingProperties.getTotalPlaces()).thenReturn(20);
        when(repository.countEntered(startDate, endDate)).thenReturn(15L);
        when(repository.countExited(startDate, endDate)).thenReturn(10L);
        when(repository.findAvgParkTimeInSeconds(startDate, endDate)).thenReturn(3600L);

        ReportDtoV2 report = service.generate(startDate, endDate);

        assertNotNull(report);
        assertEquals(15L, report.getEnteredVehiclesNumber());
        assertEquals(10L, report.getExitedVehiclesNumber());
        assertEquals(7, report.getAvailablePlaces());
        assertEquals(13, report.getOccupiedPlaces());
        assertEquals(3600L, report.getAvgParkTimeInSeconds());
    }
}