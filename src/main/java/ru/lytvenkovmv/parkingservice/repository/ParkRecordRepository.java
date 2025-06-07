package ru.lytvenkovmv.parkingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkRecordRepository extends JpaRepository<ParkRecord, UUID> {
    Optional<ParkRecord> findByRegNumberAndLeaveTimeIsNull(String regNumber);

    Optional<ParkRecord> findByIdAndRegNumber(UUID id, String regNumber);
}
