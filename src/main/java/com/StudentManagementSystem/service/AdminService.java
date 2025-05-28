package com.StudentManagementSystem.service;

import com.StudentManagementSystem.models.Course;
import com.StudentManagementSystem.models.Student;

import java.util.List;

public interface AdminService {
    List<Student> getAllStudent();
    Student addStudent(Student student);
    Course addCourse(Course course);
    void assignCourseToStudent(Long studentId, Long courseId);
    List<Student> findStudentsByName(String name);
    List<Student> findStudentsByCourseId(Long courseId);
}
