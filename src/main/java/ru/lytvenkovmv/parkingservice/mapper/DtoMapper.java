package ru.lytvenkovmv.parkingservice.mapper;

import org.mapstruct.Mapper;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingResponse;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingResponse;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface DtoMapper {
    EnterParkingRequest enterParkingRequestFrom(EnterRequestDto requestDto, LocalDateTime enterTime);

    ExitParkingRequest exitParkingRequestFrom(ExitRequestDto requestDto, LocalDateTime exitTime);

    EnterResponseDto enterResponseDtoFrom(EnterParkingResponse response);

    ExitResponseDto exitResponseDtoFrom(ExitParkingResponse response);
}
