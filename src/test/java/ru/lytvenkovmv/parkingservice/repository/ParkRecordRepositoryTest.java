package ru.lytvenkovmv.parkingservice.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ParkRecordRepositoryTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    ParkRecordRepository repository;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    @Sql(statements = {"""
            TRUNCATE TABLE park_records;
            """})
    void init() {
    }

    @Test
    @Sql(statements = {"""
            INSERT INTO park_records (id, reg_number, type, enter_time, exit_time, created_at, updated_at) VALUES
                        ('11111111-1111-1111-1111-111111111111', 'А111ВС150', 'CAR', '2024-06-01T10:00:00', NULL, '2025-01-01T10:00:00', '2025-01-01T10:00:00'),
                        ('22222222-2222-2222-2222-222222222222', 'А222ВС150', 'CAR', '2024-06-02T12:00:00', '2024-06-02T13:00:00', '2025-01-01T10:00:00', '2025-01-01T10:00:00'),
                        ('33333333-3333-3333-3333-333333333333', 'А333ВС150', 'CAR', '2024-05-31T09:00:00', NULL, '2025-01-01T10:00:00', '2025-01-01T10:00:00');
            """})
    void when_countEntered_then_return_count() {
        LocalDateTime startTime = LocalDateTime.of(2024, 6, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 6, 2, 23, 59);

        long result = repository.countEntered(startTime, endTime);

        assertEquals(2, result);
    }

    @Test
    @Sql(statements = {"""
            INSERT INTO park_records (id, reg_number, type, enter_time, exit_time, created_at, updated_at) VALUES
                        ('11111111-1111-1111-1111-111111111111', 'А111ВС150', 'CAR', '2024-06-01T10:00:00', NULL, '2025-01-01T10:00:00', '2025-01-01T10:00:00'),
                        ('22222222-2222-2222-2222-222222222222', 'А222ВС150', 'CAR', '2024-06-02T12:00:00', '2024-06-02T13:00:00', '2025-01-01T10:00:00', '2025-01-01T10:00:00'),
                        ('33333333-3333-3333-3333-333333333333', 'А333ВС150', 'CAR', '2024-05-31T09:00:00', NULL, '2025-01-01T10:00:00', '2025-01-01T10:00:00');
            """})
    void when_findByRegNumberAndExitTimeIsNull_then_return_record() {
        Optional<ParkRecord> result = repository.findByRegNumberAndExitTimeIsNull("А111ВС150");
        assertTrue(result.isPresent());
        assertEquals("А111ВС150", result.get().getRegNumber());
    }

    @Test
    @Sql(statements = {"""
            INSERT INTO park_records (id, reg_number, type, enter_time, exit_time, created_at, updated_at) VALUES
                        ('11111111-1111-1111-1111-111111111111', 'А111ВС150', 'CAR', '2024-06-01T10:00:00', NULL, '2025-01-01T10:00:00', '2025-01-01T10:00:00'),
                        ('22222222-2222-2222-2222-222222222222', 'А222ВС150', 'CAR', '2024-06-02T12:00:00', '2024-06-02T13:00:00', '2025-01-01T10:00:00', '2025-01-01T10:00:00'),
                        ('33333333-3333-3333-3333-333333333333', 'А333ВС150', 'CAR', '2024-05-31T09:00:00', NULL, '2025-01-01T10:00:00', '2025-01-01T10:00:00');
            """})
    void when_countAllByExitTimeIsNull_then_return_count() {
        int result = repository.countAllByExitTimeIsNull();

        assertEquals(2, result);
    }

    @Test
    @Sql(statements = {"""
            INSERT INTO park_records (id, reg_number, type, enter_time, exit_time, created_at, updated_at) VALUES
                        ('11111111-1111-1111-1111-111111111111', 'А111ВС150', 'CAR', '2024-06-01T10:00:00', NULL, '2025-01-01T10:00:00', '2025-01-01T10:00:00'),
                        ('22222222-2222-2222-2222-222222222222', 'А222ВС150', 'CAR', '2024-06-02T12:00:00', '2024-06-02T13:00:00', '2025-01-01T10:00:00', '2025-01-01T10:00:00'),
                        ('33333333-3333-3333-3333-333333333333', 'А333ВС150', 'CAR', '2024-05-31T09:00:00', NULL, '2025-01-01T10:00:00', '2025-01-01T10:00:00');
            """})
    void when_countExited_then_return_count() {
        LocalDateTime startTime = LocalDateTime.of(2024, 6, 2, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 6, 2, 23, 59);

        long result = repository.countExited(startTime, endTime);

        assertEquals(1, result);
    }

    @Test
    @Sql(statements = {"""
            INSERT INTO park_records (id, reg_number, type, enter_time, exit_time, created_at, updated_at) VALUES
                        ('11111111-1111-1111-1111-111111111111', 'А111ВС150', 'CAR', '2024-06-02T12:00:00', '2024-06-02T13:00:00', '2025-01-01T10:00:00', '2025-01-01T10:00:00'),
                        ('22222222-2222-2222-2222-222222222222', 'А222ВС150', 'CAR', '2024-06-02T14:00:00', '2024-06-02T14:30:00', '2025-01-01T10:00:00', '2025-01-01T10:00:00'),
                        ('33333333-3333-3333-3333-333333333333', 'А333ВС150', 'CAR', '2024-06-01T09:00:00', NULL, '2025-01-01T10:00:00', '2025-01-01T10:00:00');
            """})
    void when_findAvgParkTimeInSeconds_then_return_average_of_two_records() {
        LocalDateTime startTime = LocalDateTime.of(2024, 6, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 6, 3, 0, 0);

        long result = repository.findAvgParkTimeInSeconds(startTime, endTime);

        assertEquals(2700L, result);
    }
}
