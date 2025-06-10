package ru.lytvenkovmv.parkingservice.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lytvenkovmv.parkingservice.properties.ParkingProperties;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingUtilTest {
    @Mock
    private ParkingProperties properties;
    @Mock
    private ParkRecordRepository repository;
    @InjectMocks
    private ParkingUtil parkingUtil;

    @Test
    void calcAvailablePlaces_returnsCorrectValue() {
        when(properties.getTotalPlaces()).thenReturn(100);
        when(repository.countAllByExitTimeIsNull()).thenReturn(35);

        int result = parkingUtil.calcAvailablePlaces();

        assertEquals(65, result);
    }
}