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
    Optional<ParkRecord> findByRegNumberAndLeaveTimeIsNull(String regNumber);

    Optional<ParkRecord> findByIdAndRegNumber(UUID id, String regNumber);

    @Query("""
                SELECT COUNT(pr) FROM ParkRecord pr
                  WHERE pr.enterTime > :startDate
                  AND pr.enterTime < :endDate
            """)
    Long countEntered(LocalDateTime startDate, LocalDateTime endDate);

    @Query("""
                SELECT COUNT(pr) FROM ParkRecord pr
                WHERE pr.leaveTime IS NOT NULL
                  AND pr.leaveTime > :startDate
                  AND pr.leaveTime < :endDate
            """)
    Long countLeaved(LocalDateTime startDate, LocalDateTime endDate);

    @Query("""
                SELECT COUNT(pr) FROM ParkRecord pr
                WHERE pr.leaveTime IS NOT NULL
            """)
    Long findAvgParkTimeInSeconds(LocalDateTime startDate, LocalDateTime endDate);
}
