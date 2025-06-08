package ru.lytvenkovmv.parkingservice.service;

import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;

import java.util.List;

public interface ParkRecordsService {
    List<ParkRecordResponseDto> findAll(PageableDto pageableDto);

    ParkRecordResponseDto findById(String id);
}
