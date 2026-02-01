package kz.iitu.lab2.dto;

public record StudentResponse(
        Long id,
        String fullName,
        String email,
        Integer enrollmentYear
) {
}
