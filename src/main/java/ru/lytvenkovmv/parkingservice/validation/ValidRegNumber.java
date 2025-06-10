package ru.lytvenkovmv.parkingservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RegNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidRegNumber {
    String message() default "Неверный формат регистрационного номера т/с. Пример: А123ВЕ150";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
