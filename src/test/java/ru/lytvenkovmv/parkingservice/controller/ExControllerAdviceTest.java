package ru.lytvenkovmv.parkingservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ExControllerAdvice.class, TestController.class})
class ExControllerAdviceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testParkingExceptionHandler() throws Exception {
        mockMvc.perform(get("/parking-ex"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.type", is("Бизнес-ошибка")))
                .andExpect(jsonPath("$.message", is("К сожалению нет свободных мест")));
    }

    @Test
    void testValidationExceptionHandler() throws Exception {
        String requestBody = "{\"id\": null}";

        mockMvc.perform(post("/validation-ex")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.type", is("Ошибка валидации тела запроса")))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testGenericExceptionHandler() throws Exception {
        mockMvc.perform(get("/other-ex"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.type", is("Непредвиденная ошибка")))
                .andExpect(jsonPath("$.message", is("Что-то пошло не так")));
    }
}