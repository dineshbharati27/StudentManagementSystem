package com.StudentManagementSystem.repository;

import com.StudentManagementSystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCase(String name);
    Optional<Student> findByStudentCodeAndDateOfBirth(String student_code, java.time.LocalDate dob);
}
