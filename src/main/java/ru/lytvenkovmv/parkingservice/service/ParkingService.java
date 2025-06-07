package ru.lytvenkovmv.parkingservice.service;

import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.LeaveRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.LeaveResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;

import java.util.List;

public interface ParkingService {
    List<ParkRecordResponseDto> findAll(PageableDto pageableDto);

    ParkRecordResponseDto findById(String id);

    EnterResponseDto enterParking(EnterRequestDto requestDto);

    LeaveResponseDto leaveParking(LeaveRequestDto requestDto);
}
