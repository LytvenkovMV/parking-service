package ru.lytvenkovmv.parkingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkRecordRepository extends JpaRepository<ParkRecord, UUID> {
    Optional<ParkRecord> findByRegNumberAndExitTimeIsNull(String regNumber);

    Integer countAllByExitTimeIsNull();

    @Query("""
                SELECT COUNT(pr) FROM ParkRecord pr
                WHERE pr.enterTime >= :startDate
                AND pr.enterTime <= :endDate
            """)
    Long countEntered(LocalDateTime startDate, LocalDateTime endDate);

    @Query("""
                SELECT COUNT(pr) FROM ParkRecord pr
                WHERE pr.exitTime IS NOT NULL
                AND pr.exitTime >= :startDate
                AND pr.exitTime <= :endDate
            """)
    Long countExited(LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = """
                SELECT AVG(EXTRACT(EPOCH FROM (pr.exit_time - pr.enter_time)))
                FROM park_records pr
                WHERE pr.exit_time IS NOT NULL
                AND pr.enter_time >= :startDate
                AND pr.exit_time <= :endDate
            """,
            nativeQuery = true)
    Long findAvgParkTimeInSeconds(LocalDateTime startDate, LocalDateTime endDate);
}
