package ru.lytvenkovmv.parkingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.LeaveRequestDto;
import ru.lytvenkovmv.parkingservice.dto.parking.LeaveResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.service.ParkingService;
import ru.lytvenkovmv.parkingservice.validation.ValidUUID;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking/api/v1")
public class ParkingController {
    private final ParkingService parkingService;

    @PostMapping("/records")
    List<ParkRecordResponseDto> getAll(@RequestBody @Validated PageableDto pageableDto) {
        return parkingService.findAll(pageableDto);
    }

    @GetMapping("/records/{id}")
    ParkRecordResponseDto getById(@PathVariable @ValidUUID String id) {
        return parkingService.findById(id);
    }

    @PostMapping("/enter")
    EnterResponseDto enter(@RequestBody @Validated EnterRequestDto requestDto) {
        return parkingService.enterParking(requestDto);
    }

    @PostMapping("/leave")
    LeaveResponseDto leave(@RequestBody @Validated LeaveRequestDto requestDto) {
        return parkingService.leaveParking(requestDto);
    }
}
