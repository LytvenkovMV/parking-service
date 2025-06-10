package ru.lytvenkovmv.parkingservice.service;

public interface ParkingService<R1, R2, V1, V2> {
    V1 enterParking(R1 requestDto);

    V2 exitParking(R2 requestDto);
}
