package ru.lytvenkovmv.parkingservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UuidValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidUUID {
    String message() default "Неверный формат UUID. Пример: fd3f40dc-97f3-42ff-bb25-47ad6ff13e1b";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
