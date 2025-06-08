package ru.lytvenkovmv.parkingservice.mapper.v2;

import org.mapstruct.Mapper;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingResponse;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingResponse;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.v2.EnterRequestDtoV2;
import ru.lytvenkovmv.parkingservice.dto.parking.v2.ExitRequestDtoV2;

@Mapper(componentModel = "spring")
public interface DtoMapperV2 {
    EnterParkingRequest enterParkingRequestFrom(EnterRequestDtoV2 requestDto);

    ExitParkingRequest exitParkingRequestFrom(ExitRequestDtoV2 requestDto);

    EnterResponseDto enterResponseDtoFrom(EnterParkingResponse response);

    ExitResponseDto exitResponseDtoFrom(ExitParkingResponse response);
}
