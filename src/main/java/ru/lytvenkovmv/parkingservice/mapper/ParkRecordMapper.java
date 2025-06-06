package ru.lytvenkovmv.parkingservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lytvenkovmv.parkingservice.dto.car.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.car.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.car.LeaveResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;

@Mapper(componentModel = "spring")
public interface ParkRecordMapper {
    EnterResponseDto enterResponseDtoFrom(ParkRecord parkRecord);

    LeaveResponseDto leaveResponseDtoFrom(ParkRecord parkRecord);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "leaveTime", ignore = true)
    ParkRecord parkRecordFrom(EnterRequestDto requestDto);
}
