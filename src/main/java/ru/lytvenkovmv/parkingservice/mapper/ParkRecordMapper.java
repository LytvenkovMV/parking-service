package ru.lytvenkovmv.parkingservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.enums.VehicleType;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ParkRecordMapper {
    ParkRecordResponseDto parkRecordResponseDtoFrom(ParkRecord record);

    EnterResponseDto enterResponseDtoFrom(ParkRecord record);

    ExitResponseDto exitResponseDtoFrom(ParkRecord record);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "type", expression = "java(this.mapType(requestDto))")
    @Mapping(target = "exitTime", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ParkRecord parkRecordFrom(EnterRequestDto requestDto, LocalDateTime enterTime);

    default VehicleType mapType(EnterRequestDto requestDto) {
        return VehicleType.valueOf(requestDto.getType().toUpperCase());
    }
}
