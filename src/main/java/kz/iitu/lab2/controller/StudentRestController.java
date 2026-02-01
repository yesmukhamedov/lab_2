package kz.iitu.lab2.controller;

import jakarta.validation.Valid;
import kz.iitu.lab2.dto.StudentRequest;
import kz.iitu.lab2.dto.StudentResponse;
import kz.iitu.lab2.entity.Student;
import kz.iitu.lab2.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponse> list() {
        return studentService.findAll().stream()
                .map(StudentRestController::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return toResponse(studentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequest request,
                                                  UriComponentsBuilder uriBuilder) {
        Student created = studentService.create(toEntity(request));
        URI location = uriBuilder.path("/api/students/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(toResponse(created));
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        Student updated = studentService.update(id, toEntity(request));
        return toResponse(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    private static Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.setFullName(request.fullName());
        student.setEmail(request.email());
        student.setEnrollmentYear(request.enrollmentYear());
        return student;
    }

    private static StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFullName(),
                student.getEmail(),
                student.getEnrollmentYear()
        );
    }
}
