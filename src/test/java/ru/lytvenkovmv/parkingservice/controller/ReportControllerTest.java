package ru.lytvenkovmv.parkingservice.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.lytvenkovmv.parkingservice.dto.report.ReportDto;
import ru.lytvenkovmv.parkingservice.service.impl.ReportServiceImpl;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @MockBean
    ReportServiceImpl reportService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void when_generateReport_then_return_reportDto() {
        LocalDateTime startDate = LocalDateTime.of(2024, 6, 1, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 6, 30, 23, 59, 59);

        ReportDto reportDto = ReportDto.builder()
                .enteredVehiclesNumber(15L)
                .exitedVehiclesNumber(10L)
                .avgParkTimeInSeconds(3600L)
                .build();

        when(reportService.generate(startDate, endDate)).thenReturn(reportDto);

        mockMvc.perform(get("/parking/api/v1/reports")
                        .param("startDate", "2024-06-01T00:00:00")
                        .param("endDate", "2024-06-30T23:59:59")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enteredVehiclesNumber", is(15)))
                .andExpect(jsonPath("$.exitedVehiclesNumber", is(10)))
                .andExpect(jsonPath("$.avgParkTimeInSeconds", is(3600)));
    }
}