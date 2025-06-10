package ru.lytvenkovmv.parkingservice.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties(prefix = "parking-service.parking")
public class ParkingProperties {
    @NotNull(message = "Количество парковочных мест должно быть задано")
    @Range(min = 1, max = 100000, message = "Количество парковочных мест должно быть от 1 до 100000")
    private Integer totalPlaces;
}
