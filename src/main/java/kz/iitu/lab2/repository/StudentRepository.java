package kz.iitu.lab2.repository;

import kz.iitu.lab2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
