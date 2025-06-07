package ru.lytvenkovmv.parkingservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.exception.ParkingException;
import ru.lytvenkovmv.parkingservice.mapper.PageableMapper;
import ru.lytvenkovmv.parkingservice.mapper.ParkRecordMapper;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;
import ru.lytvenkovmv.parkingservice.service.ParkingService;
import ru.lytvenkovmv.parkingservice.util.ParkingUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {
    private final ParkRecordRepository repository;
    private final ParkRecordMapper mapper;
    private final ParkingUtil parkingUtil;

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
        if (parkingUtil.calcAvailablePlaces() < 1) {
            throw new ParkingException("К сожалению свободных мест нет");
        }

        LocalDateTime enterTime = LocalDateTime.now();
        ParkRecord parkRecord = mapper.parkRecordFrom(requestDto, enterTime);
        String regNumber = parkRecord.getRegNumber();
        repository.findByRegNumberAndExitTimeIsNull(regNumber)
                .ifPresent(record -> {
                    throw new ParkingException("Т/с с рег. номером " + regNumber + " уже на парковке");
                });
        repository.save(parkRecord);

        log.info("На парковку въехал автомобиль {} рег. номер {}. ID записи в БД: {}", parkRecord.getType(), parkRecord.getRegNumber(), parkRecord.getId());

        return mapper.enterResponseDtoFrom(parkRecord);
    }

    @Override
    public ExitResponseDto exitParking(ExitRequestDto requestDto) {
        String regNumber = requestDto.getRegNumber();
        LocalDateTime exitTime = LocalDateTime.now();
        Optional<ParkRecord> parkRecordOpt = repository.findByRegNumberAndExitTimeIsNull(regNumber);
        ParkRecord parkRecord = parkRecordOpt
                .orElseThrow(() -> new ParkingException("Автомобиль с рег. номером " + regNumber + " отсутствует на парковке"));

        if (parkRecord.getEnterTime().isAfter(exitTime)) {
            throw new ParkingException("Время выезда раньше, чем время заезда");
        }
        parkRecord.setExitTime(exitTime);
        repository.save(parkRecord);

        log.info("С парковки выехал автомобиль {} рег. номер {}. ID записи в БД: {}", parkRecord.getType(), parkRecord.getRegNumber(), parkRecord.getId());

        return mapper.exitResponseDtoFrom(parkRecord);
    }
}
