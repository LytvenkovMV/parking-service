package ru.lytvenkovmv.parkingservice.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.enums.VehicleType;
import ru.lytvenkovmv.parkingservice.exception.ParkingException;
import ru.lytvenkovmv.parkingservice.mapper.ParkRecordMapper;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;
import ru.lytvenkovmv.parkingservice.util.ParkingUtil;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingServiceImplEnterParkingTest {
    @Mock
    private ParkRecordRepository repository;
    @Mock
    private ParkRecordMapper mapper;
    @Mock
    private ParkingUtil parkingUtil;
    @InjectMocks
    private ParkingServiceImpl service;

    @Test
    void when_enterParking_success_then_return_dto() {
        EnterRequestDto request = new EnterRequestDto();
        request.setRegNumber("А123ВС150");
        request.setType("CAR");

        when(parkingUtil.calcAvailablePlaces()).thenReturn(3);

        LocalDateTime enterTime = LocalDateTime.now();
        ParkRecord parkRecord = new ParkRecord();
        parkRecord.setId(UUID.randomUUID());
        parkRecord.setRegNumber("А123ВС150");
        parkRecord.setType(VehicleType.CAR);
        parkRecord.setEnterTime(enterTime);

        when(mapper.parkRecordFrom(eq(request), any(LocalDateTime.class))).thenReturn(parkRecord);
        when(repository.findByRegNumberAndExitTimeIsNull("А123ВС150")).thenReturn(Optional.empty());
        when(repository.save(parkRecord)).thenReturn(parkRecord);

        EnterResponseDto responseDto = new EnterResponseDto();
        responseDto.setId(parkRecord.getId().toString());
        responseDto.setRegNumber("А123ВС150");
        responseDto.setEnterTime(enterTime);

        when(mapper.enterResponseDtoFrom(parkRecord)).thenReturn(responseDto);

        EnterResponseDto result = service.enterParking(request);

        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        assertEquals("А123ВС150", result.getRegNumber());
        assertEquals(enterTime, result.getEnterTime());
    }

    @Test
    void when_enterParking_and_no_available_places_then_exception() {
        EnterRequestDto request = new EnterRequestDto();
        request.setRegNumber("А123ВС150");
        request.setType("CAR");

        when(parkingUtil.calcAvailablePlaces()).thenReturn(0);

        assertThrows(ParkingException.class, () -> service.enterParking(request));
    }

    @Test
    void when_enterParking_and_car_already_inside_then_exception() {
        EnterRequestDto request = new EnterRequestDto();
        request.setRegNumber("А123ВС150");
        request.setType("CAR");

        when(parkingUtil.calcAvailablePlaces()).thenReturn(2);
        LocalDateTime enterTime = LocalDateTime.now();
        ParkRecord parkRecord = new ParkRecord();
        parkRecord.setId(UUID.randomUUID());
        parkRecord.setRegNumber("А123ВС150");
        parkRecord.setType(VehicleType.CAR);
        parkRecord.setEnterTime(enterTime);

        when(mapper.parkRecordFrom(eq(request), any(LocalDateTime.class))).thenReturn(parkRecord);
        when(repository.findByRegNumberAndExitTimeIsNull("А123ВС150")).thenReturn(Optional.of(parkRecord));

        assertThrows(ParkingException.class, () -> service.enterParking(request));
    }
}