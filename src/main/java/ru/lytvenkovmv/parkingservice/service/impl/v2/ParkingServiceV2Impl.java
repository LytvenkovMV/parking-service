package ru.lytvenkovmv.parkingservice.service.impl.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingResponse;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingResponse;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.v2.EnterRequestDtoV2;
import ru.lytvenkovmv.parkingservice.dto.parking.v2.ExitRequestDtoV2;
import ru.lytvenkovmv.parkingservice.mapper.v2.DtoMapperV2;
import ru.lytvenkovmv.parkingservice.service.CoreParkingService;
import ru.lytvenkovmv.parkingservice.service.ParkingService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParkingServiceV2Impl implements ParkingService<EnterRequestDtoV2, ExitRequestDtoV2, EnterResponseDto, ExitResponseDto> {
    private final DtoMapperV2 mapper;
    private final CoreParkingService coreParkingService;

    @Override
    public EnterResponseDto enterParking(EnterRequestDtoV2 requestDto) {
        EnterParkingRequest request = mapper.enterParkingRequestFrom(requestDto);
        EnterParkingResponse response = coreParkingService.enterParking(request);

        return mapper.enterResponseDtoFrom(response);
    }

    @Override
    public ExitResponseDto exitParking(ExitRequestDtoV2 requestDto) {
        ExitParkingRequest request = mapper.exitParkingRequestFrom(requestDto);
        ExitParkingResponse response = coreParkingService.exitParking(request);

        return mapper.exitResponseDtoFrom(response);
    }
}
