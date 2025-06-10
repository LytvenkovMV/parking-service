package ru.lytvenkovmv.parkingservice.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingResponse;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.enums.VehicleType;
import ru.lytvenkovmv.parkingservice.exception.ParkingException;
import ru.lytvenkovmv.parkingservice.mapper.ParkRecordMapper;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoreParkingServiceImplExitParkingTest {
    @Mock
    private ParkRecordRepository repository;
    @Mock
    private ParkRecordMapper mapper;
    @InjectMocks
    private CoreParkingServiceImpl service;

    @Test
    void when_exitParking_success_then_return_response() {
        LocalDateTime enterTime = LocalDateTime.of(2024, 6, 1, 8, 0);
        LocalDateTime exitTime = LocalDateTime.now();

        ExitParkingRequest request = new ExitParkingRequest();
        request.setRegNumber("А123ВС150");
        request.setExitTime(exitTime);

        ParkRecord parkRecord = new ParkRecord();
        parkRecord.setId(UUID.randomUUID());
        parkRecord.setRegNumber("А123ВС150");
        parkRecord.setType(VehicleType.CAR);
        parkRecord.setEnterTime(enterTime);

        when(repository.findByRegNumberAndExitTimeIsNull("А123ВС150")).thenReturn(Optional.of(parkRecord));
        when(repository.save(parkRecord)).thenReturn(parkRecord);

        ExitParkingResponse response = new ExitParkingResponse();
        response.setId(parkRecord.getId().toString());
        response.setRegNumber("А123ВС150");
        response.setExitTime(exitTime);

        when(mapper.exitResponseDtoFrom(parkRecord)).thenReturn(response);

        ExitParkingResponse result = service.exitParking(request);

        assertNotNull(result);
        assertEquals(response.getId(), result.getId());
        assertEquals("А123ВС150", result.getRegNumber());
        assertNotNull(result.getExitTime());
    }

    @Test
    void when_exitParking_carNotFound_then_throwException() {
        ExitParkingRequest request = new ExitParkingRequest();
        request.setRegNumber("А123ВС150");

        when(repository.findByRegNumberAndExitTimeIsNull("А123ВС150")).thenReturn(Optional.empty());

        assertThrows(ParkingException.class, () -> service.exitParking(request));
    }

    @Test
    void when_exitParking_exitBeforeEnter_then_throwException() {
        LocalDateTime exitTime = LocalDateTime.now();
        LocalDateTime enterTime = exitTime.plusHours(1);

        ExitParkingRequest request = new ExitParkingRequest();
        request.setRegNumber("А123ВС150");
        request.setExitTime(exitTime);

        ParkRecord parkRecord = new ParkRecord();
        parkRecord.setId(UUID.randomUUID());
        parkRecord.setRegNumber("А123ВС150");
        parkRecord.setType(VehicleType.CAR);
        parkRecord.setEnterTime(enterTime);

        when(repository.findByRegNumberAndExitTimeIsNull("А123ВС150")).thenReturn(Optional.of(parkRecord));

        assertThrows(ParkingException.class, () -> service.exitParking(request));
    }
}