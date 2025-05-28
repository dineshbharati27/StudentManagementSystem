package com.StudentManagementSystem.service.implementation;


import com.StudentManagementSystem.exception.ApiException;
import com.StudentManagementSystem.models.Address;
import com.StudentManagementSystem.models.Course;
import com.StudentManagementSystem.models.Student;
import com.StudentManagementSystem.repository.CourseRepository;
import com.StudentManagementSystem.repository.StudentRepository;
import com.StudentManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Student> getAllStudent(){
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        Optional<Student> newStudent = studentRepository.findByStudentCodeAndDateOfBirth(student.getStudentCode(), student.getDateOfBirth());
        if(newStudent.isPresent()) throw new ApiException("student already exist with student code: " + student.getStudentCode());

        if(student.getAddresses() != null) {
            for(Address address : student.getAddresses()) {
                address.setStudent(student);
            }
        }
        return studentRepository.save(student);
    }

    @Override
    public Course addCourse(Course course) {
        System.out.println(course);
        Optional<Course> newCourse = courseRepository.findByCourseCode(course.getCourseCode());
        if(newCourse.isPresent()) throw new ApiException("course is already exist with the course code: " + course.getCourseCode());

        if (course.getStudents() == null) {
            course.setStudents(new HashSet<>());
        }
        return courseRepository.save(course);
    }

    @Override
    public void assignCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new ApiException("Student not found with student_id: "+studentId));

        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new ApiException("course not found with course_id: "+courseId));

        student.getCourseSet().add(course);
        studentRepository.save(student);
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        List<Student> student = studentRepository.findByNameContainingIgnoreCase(name);
        if(student.isEmpty()) throw new ApiException("Student not found with name: "+name);
        return student;
    }

    @Override
    public List<Student> findStudentsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new ApiException("course not found with course_id: "+courseId));
        if(course.getStudents().isEmpty()) throw new ApiException("0 student enroll in this course with id: "+course);

        return List.copyOf(course.getStudents());
    }
}
