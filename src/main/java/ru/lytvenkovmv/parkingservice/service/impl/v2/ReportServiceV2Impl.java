package ru.lytvenkovmv.parkingservice.service.impl.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.parkingservice.dto.report.v2.ReportDtoV2;
import ru.lytvenkovmv.parkingservice.properties.ParkingProperties;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;
import ru.lytvenkovmv.parkingservice.service.ReportService;
import ru.lytvenkovmv.parkingservice.util.ParkingUtil;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportServiceV2Impl implements ReportService<ReportDtoV2> {
    private final ParkRecordRepository repository;
    private final ParkingProperties parkingProperties;
    private final ParkingUtil parkingUtil;

    @Override
    public ReportDtoV2 generate(LocalDateTime startDate, LocalDateTime endDate) {
        int availablePlaces = parkingUtil.calcAvailablePlaces();
        int occupiedPlaces = parkingProperties.getTotalPlaces() - availablePlaces;

        return ReportDtoV2.builder()
                .enteredVehiclesNumber(repository.countEntered(startDate, endDate))
                .exitedVehiclesNumber(repository.countExited(startDate, endDate))
                .availablePlaces(availablePlaces)
                .occupiedPlaces(occupiedPlaces)
                .avgParkTimeInSeconds(repository.findAvgParkTimeInSeconds(startDate, endDate))
                .build();
    }
}
