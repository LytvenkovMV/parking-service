package ru.lytvenkovmv.parkingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.lytvenkovmv.parkingservice.enums.VehicleType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
public class ParkRecord extends AuditableEntity {
    /**
     * Уникальный идентификатор записи
     */
    @Id
    private UUID id;
    /**
     * Регистрационный номер транспортного средства
     */
    private String regNumber;
    /**
     * Тип транспортного средства
     */
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    /**
     * Дата и время заезда транспортного средства на парковку
     */
    private LocalDateTime enterTime;
    /**
     * Дата и время выезда транспортного средства с парковки
     */
    private LocalDateTime leaveTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkRecord that = (ParkRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

