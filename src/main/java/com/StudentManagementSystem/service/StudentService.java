package com.StudentManagementSystem.service;

import com.StudentManagementSystem.models.Course;
import com.StudentManagementSystem.models.Student;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {
    Student validateStudent(String studentCode, LocalDate dob);
    Student updateProfile(Long id, Student student);
    List<Course> getMyCourses(Long studentId);
    void leaveCourse(Long studentId, Long courseId);
}
