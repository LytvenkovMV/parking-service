package ru.lytvenkovmv.parkingservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.exception.ParkingException;
import ru.lytvenkovmv.parkingservice.mapper.PageableMapper;
import ru.lytvenkovmv.parkingservice.mapper.ParkRecordMapper;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;
import ru.lytvenkovmv.parkingservice.service.ParkRecordsService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParkRecordsServiceImpl implements ParkRecordsService {
    private final ParkRecordRepository repository;
    private final ParkRecordMapper mapper;

    @Override
    public List<ParkRecordResponseDto> findAll(PageableDto pageableDto) {
        Pageable pageable = PageableMapper.map(pageableDto);
        Page<ParkRecord> parkRecords = repository.findAll(pageable);

        return parkRecords.stream()
                .map(mapper::parkRecordResponseDtoFrom)
                .toList();
    }

    @Override
    public ParkRecordResponseDto findById(String id) {
        ParkRecord parkRecord = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ParkingException("Не найдена запись с ID: " + id));

        return mapper.parkRecordResponseDtoFrom(parkRecord);
    }
}
