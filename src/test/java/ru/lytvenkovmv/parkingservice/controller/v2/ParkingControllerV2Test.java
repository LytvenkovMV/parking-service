package ru.lytvenkovmv.parkingservice.controller.v2;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.lytvenkovmv.parkingservice.dto.parking.EnterResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ExitResponseDto;
import ru.lytvenkovmv.parkingservice.dto.parking.v2.EnterRequestDtoV2;
import ru.lytvenkovmv.parkingservice.dto.parking.v2.ExitRequestDtoV2;
import ru.lytvenkovmv.parkingservice.service.ParkingService;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingControllerV2.class)
class ParkingControllerV2Test {
    @MockBean
    ParkingService<EnterRequestDtoV2, ExitRequestDtoV2, EnterResponseDto, ExitResponseDto> service;
    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void when_enter_when_return_status_ok() {
        String enterRequestJson = "{\"regNumber\": \"А123ВС150\", \"type\": \"Car\", \"enterTime\": \"2024-06-01T00:00\"}";

        LocalDateTime enterTime = LocalDateTime.of(2024, 6, 1, 0, 0);
        EnterResponseDto enterResponseDto = new EnterResponseDto();
        enterResponseDto.setId("11111111-1111-1111-1111-111111111111");
        enterResponseDto.setRegNumber("А123ВС150");
        enterResponseDto.setEnterTime(enterTime);

        when(service.enterParking(any(EnterRequestDtoV2.class))).thenReturn(enterResponseDto);

        mockMvc.perform(post("/parking/api/v2/entry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(enterRequestJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("11111111-1111-1111-1111-111111111111")))
                .andExpect(jsonPath("$.regNumber", is("А123ВС150")))
                .andExpect(jsonPath("$.enterTime", is("2024-06-01T00:00:00")));
    }

    @Test
    @SneakyThrows
    void when_exit_when_return_status_ok() {
        String exitRequestJson = "{\"regNumber\": \"А123ВС150\", \"exitTime\": \"2024-06-01T00:00\"}";

        LocalDateTime exitTime = LocalDateTime.of(2024, 6, 1, 0, 0);
        ExitResponseDto exitResponseDto = new ExitResponseDto();
        exitResponseDto.setId("11111111-1111-1111-1111-111111111111");
        exitResponseDto.setRegNumber("А123ВС150");
        exitResponseDto.setExitTime(exitTime);

        when(service.exitParking(any(ExitRequestDtoV2.class))).thenReturn(exitResponseDto);

        mockMvc.perform(post("/parking/api/v2/exit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(exitRequestJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("11111111-1111-1111-1111-111111111111")))
                .andExpect(jsonPath("$.regNumber", is("А123ВС150")))
                .andExpect(jsonPath("$.exitTime", is("2024-06-01T00:00:00")));
    }
}