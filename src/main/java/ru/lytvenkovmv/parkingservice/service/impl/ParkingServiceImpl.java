package ru.lytvenkovmv.parkingservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingResponse;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingResponse;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;
import ru.lytvenkovmv.parkingservice.mapper.DtoMapper;
import ru.lytvenkovmv.parkingservice.service.CoreParkingService;
import ru.lytvenkovmv.parkingservice.service.ParkingService;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService<EnterRequestDto, ExitRequestDto, EnterResponseDto, ExitResponseDto> {
    private final DtoMapper mapper;
    private final CoreParkingService coreParkingService;

    @Override
    public EnterResponseDto enterParking(EnterRequestDto requestDto) {
        LocalDateTime enterTime = LocalDateTime.now();
        EnterParkingRequest request = mapper.enterParkingRequestFrom(requestDto, enterTime);
        EnterParkingResponse response = coreParkingService.enterParking(request);

        return mapper.enterResponseDtoFrom(response);
    }

    @Override
    public ExitResponseDto exitParking(ExitRequestDto requestDto) {
        LocalDateTime exitTime = LocalDateTime.now();
        ExitParkingRequest request = mapper.exitParkingRequestFrom(requestDto, exitTime);
        ExitParkingResponse response = coreParkingService.exitParking(request);

        return mapper.exitResponseDtoFrom(response);
    }
}
