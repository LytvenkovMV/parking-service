package ru.lytvenkovmv.parkingservice.service;

import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;

public interface ParkingService {
    EnterResponseDto enterParking(EnterRequestDto requestDto);

    ExitResponseDto exitParking(ExitRequestDto requestDto);
}
