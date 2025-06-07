package ru.lytvenkovmv.parkingservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.LeaveResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.enums.VehicleType;

@Mapper(componentModel = "spring")
public interface ParkRecordMapper {
    ParkRecordResponseDto parkRecordResponseDtoFrom(ParkRecord record);

    EnterResponseDto enterResponseDtoFrom(ParkRecord record);

    LeaveResponseDto leaveResponseDtoFrom(ParkRecord record);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "type", expression = "java(this.mapType(requestDto))")
    @Mapping(target = "leaveTime", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ParkRecord parkRecordFrom(EnterRequestDto requestDto);

    default VehicleType mapType(EnterRequestDto requestDto) {
        return VehicleType.valueOf(requestDto.getType().toUpperCase());
    }
}
