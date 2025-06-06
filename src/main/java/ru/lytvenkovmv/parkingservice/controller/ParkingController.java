package ru.lytvenkovmv.parkingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.parkingservice.dto.car.EnterRequestDto;
import ru.lytvenkovmv.parkingservice.dto.car.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.car.LeaveRequestDto;
import ru.lytvenkovmv.parkingservice.dto.car.LeaveResponseDto;
import ru.lytvenkovmv.parkingservice.service.ParkingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking/api/v1")
public class ParkingController {
    private final ParkingService parkingService;

    @PostMapping("/enter")
    EnterResponseDto enter(@RequestBody EnterRequestDto requestDto) {
        return parkingService.enterParking(requestDto);
    }

    @PostMapping("/leave")
    LeaveResponseDto leave(@RequestBody LeaveRequestDto requestDto) {
        return parkingService.leaveParking(requestDto);
    }
}
