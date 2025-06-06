package ru.lytvenkovmv.parkingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.parkingservice.dto.car.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.car.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.car.LeaveRequestDto;
import ru.lytvenkovmv.parkingservice.dto.car.LeaveResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.mapper.ParkRecordMapper;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;
import ru.lytvenkovmv.parkingservice.service.ParkingService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {
    private final ParkRecordRepository repository;
    private final ParkRecordMapper mapper;

    @Override
    public EnterResponseDto enterParking(EnterRequestDto requestDto) {
        ParkRecord parkRecord = mapper.parkRecordFrom(requestDto);
        repository.save(parkRecord);

        return mapper.enterResponseDtoFrom(parkRecord);
    }

    @Override
    public LeaveResponseDto leaveParking(LeaveRequestDto requestDto) {
        Optional<ParkRecord> parkRecordOpt = repository.findById(requestDto.getId());

        if (parkRecordOpt.isEmpty()) {



            throw new RuntimeException();



        }
        ParkRecord parkRecord = parkRecordOpt.get();
        parkRecord.setLeaveTime(requestDto.getLeaveTime());
        repository.save(parkRecord);

        return mapper.leaveResponseDtoFrom(parkRecord);
    }
}
