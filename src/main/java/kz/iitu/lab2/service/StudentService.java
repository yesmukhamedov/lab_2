package kz.iitu.lab2.service;

import kz.iitu.lab2.entity.Student;
import kz.iitu.lab2.exception.NotFoundException;
import kz.iitu.lab2.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found: " + id));
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Long id, Student updated) {
        Student existing = getById(id);
        existing.setFullName(updated.getFullName());
        existing.setEmail(updated.getEmail());
        existing.setEnrollmentYear(updated.getEnrollmentYear());
        return studentRepository.save(existing);
    }

    public void delete(Long id) {
        Student existing = getById(id);
        studentRepository.delete(existing);
    }
}
