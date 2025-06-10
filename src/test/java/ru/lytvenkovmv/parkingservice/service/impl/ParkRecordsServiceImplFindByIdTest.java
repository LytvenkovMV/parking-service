package ru.lytvenkovmv.parkingservice.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.enums.VehicleType;
import ru.lytvenkovmv.parkingservice.exception.ParkRecordNotFoundException;
import ru.lytvenkovmv.parkingservice.mapper.ParkRecordMapper;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkRecordsServiceImplFindByIdTest {
    @Mock
    private ParkRecordRepository repository;
    @Mock
    private ParkRecordMapper mapper;
    @InjectMocks
    private ParkRecordsServiceImpl service;

    @Test
    void when_findById_exists_then_return_dto() {
        String id = "11111111-1111-1111-1111-111111111111";
        UUID uuid = UUID.fromString(id);

        ParkRecord parkRecord = new ParkRecord();
        parkRecord.setId(uuid);
        parkRecord.setRegNumber("А123ВС150");
        parkRecord.setType(VehicleType.CAR);

        ParkRecordResponseDto dto = new ParkRecordResponseDto();
        dto.setId(uuid);
        dto.setRegNumber("А123ВС150");
        dto.setType("CAR");

        when(repository.findById(uuid)).thenReturn(Optional.of(parkRecord));
        when(mapper.parkRecordResponseDtoFrom(parkRecord)).thenReturn(dto);

        ParkRecordResponseDto result = service.findById(id);

        assertNotNull(result);
        assertEquals(uuid, result.getId());
        assertEquals("А123ВС150", result.getRegNumber());
        assertEquals("CAR", result.getType());
    }

    @Test
    void when_findById_not_found_then_throw_exception() {
        String id = "22222222-2222-2222-2222-222222222222";
        UUID uuid = UUID.fromString(id);

        when(repository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(ParkRecordNotFoundException.class, () -> service.findById(id));
    }


}