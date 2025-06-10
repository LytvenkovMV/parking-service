package ru.lytvenkovmv.parkingservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.EnterParkingResponse;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingRequest;
import ru.lytvenkovmv.parkingservice.domain.parking.ExitParkingResponse;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.exception.ParkingException;
import ru.lytvenkovmv.parkingservice.mapper.ParkRecordMapper;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;
import ru.lytvenkovmv.parkingservice.service.CoreParkingService;
import ru.lytvenkovmv.parkingservice.util.ParkingUtil;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoreParkingServiceImpl implements CoreParkingService {
    private final ParkRecordRepository repository;
    private final ParkRecordMapper mapper;
    private final ParkingUtil parkingUtil;

    @Override
    public EnterParkingResponse enterParking(EnterParkingRequest request) {
        if (parkingUtil.calcAvailablePlaces() < 1) {
            throw new ParkingException("К сожалению свободных мест нет");
        }

        ParkRecord parkRecord = mapper.parkRecordFrom(request);
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
    public ExitParkingResponse exitParking(ExitParkingRequest request) {
        String regNumber = request.getRegNumber();
        LocalDateTime exitTime = request.getExitTime();
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
