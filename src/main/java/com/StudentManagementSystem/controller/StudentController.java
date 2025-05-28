package com.StudentManagementSystem.controller;

import com.StudentManagementSystem.models.Course;
import com.StudentManagementSystem.models.Student;
import com.StudentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/validate")
    public Student validate(@RequestParam String code, @RequestParam String dob){
        return studentService.validateStudent(code, LocalDate.parse(dob));
    }

    @PutMapping("/{id}")
    public Student updateProfile(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateProfile(id, student);
    }

    @GetMapping("/courses/{id}")
    public List<Course> getCourse(@PathVariable Long id){
        return studentService.getMyCourses(id);
    }

    @DeleteMapping("/course/{studentId}/{courseId}")
    public void learveCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.leaveCourse(studentId, courseId);
    }
}
