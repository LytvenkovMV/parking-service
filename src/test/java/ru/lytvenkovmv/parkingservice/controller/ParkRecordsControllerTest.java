package ru.lytvenkovmv.parkingservice.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.service.ParkRecordsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkRecordsController.class)
class ParkRecordsControllerTest {
    @MockBean
    ParkRecordsService service;
    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void when_getById_when_return_record() {
        String id = "11111111-1111-1111-1111-111111111111";
        LocalDateTime enterTime = LocalDateTime.of(2024, 6, 1, 0, 0);
        LocalDateTime exitTime = LocalDateTime.of(2024, 6, 1, 23, 30);

        ParkRecordResponseDto record = new ParkRecordResponseDto();
        record.setId(UUID.fromString(id));
        record.setRegNumber("А123ВС150");
        record.setType("CAR");
        record.setEnterTime(enterTime);
        record.setExitTime(exitTime);

        when(service.findById(any(String.class))).thenReturn(record);

        mockMvc.perform(get("/parking/api/v1/records/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.regNumber", is("А123ВС150")))
                .andExpect(jsonPath("$.type", is("CAR")))
                .andExpect(jsonPath("$.enterTime", is("2024-06-01T00:00:00")))
                .andExpect(jsonPath("$.exitTime", is("2024-06-01T23:30:00")));
    }

    @Test
    @SneakyThrows
    void when_getAll_when_return_list() {
        String requestJson = "{\"page\": 0, \"size\": 10}";

        LocalDateTime enterTime1 = LocalDateTime.of(2024, 6, 1, 8, 0);
        LocalDateTime exitTime1 = LocalDateTime.of(2024, 6, 1, 9, 30);
        LocalDateTime enterTime2 = LocalDateTime.of(2024, 6, 2, 10, 0);

        ParkRecordResponseDto record1 = new ParkRecordResponseDto();
        record1.setId(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        record1.setRegNumber("А123ВС150");
        record1.setType("CAR");
        record1.setEnterTime(enterTime1);
        record1.setExitTime(exitTime1);

        ParkRecordResponseDto record2 = new ParkRecordResponseDto();
        record2.setId(UUID.fromString("22222222-2222-2222-2222-222222222222"));
        record2.setRegNumber("Б456ГД777");
        record2.setType("BUS");
        record2.setEnterTime(enterTime2);
        record2.setExitTime(null);

        List<ParkRecordResponseDto> responseList = List.of(record1, record2);

        when(service.findAll(any(PageableDto.class))).thenReturn(responseList);

        mockMvc.perform(post("/parking/api/v1/records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("11111111-1111-1111-1111-111111111111")))
                .andExpect(jsonPath("$[0].regNumber", is("А123ВС150")))
                .andExpect(jsonPath("$[0].type", is("CAR")))
                .andExpect(jsonPath("$[0].enterTime", is("2024-06-01T08:00:00")))
                .andExpect(jsonPath("$[0].exitTime", is("2024-06-01T09:30:00")))
                .andExpect(jsonPath("$[1].id", is("22222222-2222-2222-2222-222222222222")))
                .andExpect(jsonPath("$[1].regNumber", is("Б456ГД777")))
                .andExpect(jsonPath("$[1].type", is("BUS")))
                .andExpect(jsonPath("$[1].enterTime", is("2024-06-02T10:00:00")))
                .andExpect(jsonPath("$[1].exitTime").doesNotExist());
    }
}