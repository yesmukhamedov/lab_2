package kz.iitu.lab2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kz.iitu.lab2.validation.EnrollmentYear;

public record StudentRequest(
        @NotBlank
        @Size(min = 2, max = 100)
        String fullName,
        @NotBlank
        @Email
        String email,
        @EnrollmentYear
        Integer enrollmentYear
) {
}
