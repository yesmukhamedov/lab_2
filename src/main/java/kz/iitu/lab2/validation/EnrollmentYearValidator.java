package kz.iitu.lab2.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class EnrollmentYearValidator implements ConstraintValidator<EnrollmentYear, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        int currentYear = Year.now().getValue();
        return value >= 2000 && value <= currentYear;
    }
}
