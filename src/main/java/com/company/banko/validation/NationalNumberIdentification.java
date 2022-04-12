package com.company.banko.validation;

import com.company.banko.CustomAnnotation.NationalNumberValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NationalNumberIdentification implements ConstraintValidator<NationalNumberValidation, String> { {


}

    @Override
    public void initialize(NationalNumberValidation nationalNumber) {
    }

    @Override
    public boolean isValid(String numberField,
                           ConstraintValidatorContext cxt) {
        return numberField != null && numberField.matches("[0-9]+")
                && (numberField.length()  == 10);
    }
}
