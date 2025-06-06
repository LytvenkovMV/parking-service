package ru.lytvenkovmv.parkingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.parkingservice.dto.report.ReportDto;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/parking/api/v1/report")
public class ReportController {

    @GetMapping("")
    ReportDto report(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {

        return new ReportDto();
    }
}
