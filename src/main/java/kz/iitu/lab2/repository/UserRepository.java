package kz.iitu.lab2.repository;

import kz.iitu.lab2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
