package ru.lytvenkovmv.parkingservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.parkingservice.dto.car.EnterVehicleRequestDto;
import ru.lytvenkovmv.parkingservice.dto.car.EnterVehicleResponseDto;
import ru.lytvenkovmv.parkingservice.dto.car.LeaveVehicleRequestDto;
import ru.lytvenkovmv.parkingservice.dto.car.LeaveVehicleResponseDto;

@RestController
@RequestMapping("parking/api/v1/car")
public class CarController {

    @PostMapping("/enter")
    EnterVehicleResponseDto enter(@RequestBody EnterVehicleRequestDto requestDto) {
        return new EnterVehicleResponseDto();
    }

    @PostMapping("/leave")
    LeaveVehicleResponseDto leave(@RequestBody LeaveVehicleRequestDto requestDto) {
        return new LeaveVehicleResponseDto();
    }
}
