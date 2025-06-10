package ru.lytvenkovmv.parkingservice.service;

import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingResponse;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingResponse;

public interface CoreParkingService {
    EnterParkingResponse enterParking(EnterParkingRequest request);

    ExitParkingResponse exitParking(ExitParkingRequest request);
}
