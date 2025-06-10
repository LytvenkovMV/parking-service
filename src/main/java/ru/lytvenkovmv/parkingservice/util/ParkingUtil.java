package ru.lytvenkovmv.parkingservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lytvenkovmv.parkingservice.properties.ParkingProperties;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;

@Component
@RequiredArgsConstructor
public class ParkingUtil {
    private final ParkingProperties properties;
    private final ParkRecordRepository repository;

    public int calcAvailablePlaces() {
        return properties.getTotalPlaces() - repository.countAllByExitTimeIsNull();
    }
}
