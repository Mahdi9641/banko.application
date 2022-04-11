package com.company.banko.CustomAnnotation;

import com.company.banko.validation.NationalNumberIdentification;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NationalNumberIdentification.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NationalNumberValidation {
    String message() default "Invalid national number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}