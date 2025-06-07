package ru.lytvenkovmv.parkingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.exception.ParkingException;
import ru.lytvenkovmv.parkingservice.mapper.PageableMapper;
import ru.lytvenkovmv.parkingservice.mapper.ParkRecordMapper;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;
import ru.lytvenkovmv.parkingservice.service.ParkingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {
    private final ParkRecordRepository repository;
    private final ParkRecordMapper mapper;

    @Override
    public List<ParkRecordResponseDto> findAll(PageableDto pageableDto) {
        Pageable pageable = PageableMapper.map(pageableDto);
        Page<ParkRecord> parkRecords = repository.findAll(pageable);

        return parkRecords.stream()
                .map(mapper::parkRecordResponseDtoFrom)
                .toList();
    }

    @Override
    public ParkRecordResponseDto findById(String id) {
        ParkRecord parkRecord = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ParkingException("Не найдена запись с ID: " + id));

        return mapper.parkRecordResponseDtoFrom(parkRecord);
    }

    @Override
    public EnterResponseDto enterParking(EnterRequestDto requestDto) {
        LocalDateTime enterTime = LocalDateTime.now();
        ParkRecord parkRecord = mapper.parkRecordFrom(requestDto, enterTime);

        String regNumber = parkRecord.getRegNumber();
        repository.findByRegNumberAndLeaveTimeIsNull(regNumber)
                .ifPresent(record -> {
                    throw new ParkingException("Т/с с рег. номером " + regNumber + " уже на парковке");
                });
        repository.save(parkRecord);

        return mapper.enterResponseDtoFrom(parkRecord);
    }

    @Override
    public ExitResponseDto exitParking(ExitRequestDto requestDto) {
        UUID id = UUID.fromString(requestDto.getId());
        String regNumber = requestDto.getRegNumber();
        LocalDateTime leaveTime = LocalDateTime.now();
        Optional<ParkRecord> parkRecordOpt = repository.findByIdAndRegNumber(id, regNumber);

        ParkRecord parkRecord = parkRecordOpt.orElseThrow(() -> new ParkingException("Не найдена запись в БД с ID: " + id + " и рег. номером " + regNumber));
        if (Objects.nonNull(parkRecord.getLeaveTime())) {
            throw new ParkingException("Т/с с рег. номером " + regNumber + " уже покинуло парковку " + parkRecord.getLeaveTime());
        } else if (parkRecord.getEnterTime().isAfter(leaveTime)) {
            throw new ParkingException("Время выезда раньше, чем время заезда");
        }

        parkRecord.setLeaveTime(leaveTime);
        repository.save(parkRecord);

        return mapper.exitResponseDtoFrom(parkRecord);
    }
}
