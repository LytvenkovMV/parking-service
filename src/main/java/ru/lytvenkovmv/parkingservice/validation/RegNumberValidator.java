package ru.lytvenkovmv.parkingservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class RegNumberValidator implements ConstraintValidator<ValidRegNumber, String> {
    private static final String REGEX = "^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}(0[1-9]|[1-9]\\d|[1-9]\\d{2})$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }

        return value.matches(REGEX);
    }
}
