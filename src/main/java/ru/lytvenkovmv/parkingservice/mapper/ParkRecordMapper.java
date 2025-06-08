package ru.lytvenkovmv.parkingservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingResponse;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingResponse;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.enums.VehicleType;

@Mapper(componentModel = "spring")
public interface ParkRecordMapper {
    ParkRecordResponseDto parkRecordResponseDtoFrom(ParkRecord record);

    EnterParkingResponse enterResponseDtoFrom(ParkRecord record);

    ExitParkingResponse exitResponseDtoFrom(ParkRecord record);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "type", expression = "java(this.mapType(request))")
    @Mapping(target = "exitTime", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ParkRecord parkRecordFrom(EnterParkingRequest request);

    default VehicleType mapType(EnterParkingRequest request) {
        return VehicleType.valueOf(request.getType().toUpperCase());
    }
}
