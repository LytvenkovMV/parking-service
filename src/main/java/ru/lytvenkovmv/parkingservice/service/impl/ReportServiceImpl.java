package ru.lytvenkovmv.parkingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.parkingservice.dto.report.ReportDto;
import ru.lytvenkovmv.parkingservice.properties.ParkingProperties;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;
import ru.lytvenkovmv.parkingservice.service.ReportService;
import ru.lytvenkovmv.parkingservice.util.ParkingUtil;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ParkRecordRepository repository;
    private final ParkingProperties parkingProperties;
    private final ParkingUtil parkingUtil;

    @Override
    public ReportDto generate(LocalDateTime startDate, LocalDateTime endDate) {
        int availablePlaces = parkingUtil.calcAvailablePlaces();
        int occupiedPlaces = parkingProperties.getTotalPlaces() - availablePlaces;

        return ReportDto.builder()
                .enteredVehiclesNumber(repository.countEntered(startDate, endDate))
                .exitedVehiclesNumber(repository.countExited(startDate, endDate))
                .availablePlaces(availablePlaces)
                .occupiedPlaces(occupiedPlaces)
                .avgParkTimeInSeconds(repository.findAvgParkTimeInSeconds(startDate, endDate))
                .build();
    }
}
