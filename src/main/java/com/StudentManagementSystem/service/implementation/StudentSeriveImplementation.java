package com.StudentManagementSystem.service.implementation;

import com.StudentManagementSystem.exception.ApiException;
import com.StudentManagementSystem.models.Address;
import com.StudentManagementSystem.models.Course;
import com.StudentManagementSystem.models.Student;
import com.StudentManagementSystem.repository.CourseRepository;
import com.StudentManagementSystem.repository.StudentRepository;
import com.StudentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentSeriveImplementation implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student validateStudent(String studentCode, LocalDate dob) {
        return studentRepository.findByStudentCodeAndDateOfBirth(studentCode, dob).orElseThrow(()->
                new ApiException("Invalid Credential"));
    }

    @Override
    public Student updateProfile(Long studentId, Student newStudent) {
        Student oldStudent = studentRepository.findById(studentId).orElseThrow(() ->
                new ApiException("Student not found with id: "+studentId));

        if(newStudent.getName() != null) {
            oldStudent.setName(newStudent.getName());
        }
        if(newStudent.getEmail() != null) {
            oldStudent.setEmail(newStudent.getEmail());
        }
        if(newStudent.getMobile() != null) {
            oldStudent.setMobile(newStudent.getMobile());
        }

        // Handle addresses update properly
        if(newStudent.getAddresses() != null) {
            // Clear existing addresses (will trigger orphan removal)
            oldStudent.getAddresses().clear();

            // Add all new addresses and set the student reference
            for(Address address : newStudent.getAddresses()) {
                address.setStudent(oldStudent);
                oldStudent.getAddresses().add(address);
            }
        }

        return studentRepository.save(oldStudent);
    }

    @Override
    public List<Course> getMyCourses(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new ApiException("student not found with id: "+studentId));
        return List.copyOf(student.getCourseSet());
    }

    @Override
    public void leaveCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new ApiException("student not found with id: "+studentId));

        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new ApiException("course not found with id:"+courseId));

        student.getCourseSet().remove(course);

        studentRepository.save(student);

    }
}
