package ru.lytvenkovmv.parkingservice.service;

import ru.lytvenkovmv.parkingservice.dto.car.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.car.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.car.LeaveRequestDto;
import ru.lytvenkovmv.parkingservice.dto.car.LeaveResponseDto;

public interface ParkingService {
    EnterResponseDto enterParking(EnterRequestDto requestDto);

    LeaveResponseDto leaveParking(LeaveRequestDto requestDto);
}
