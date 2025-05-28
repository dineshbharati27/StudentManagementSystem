package com.StudentManagementSystem.controller;


import com.StudentManagementSystem.models.Course;
import com.StudentManagementSystem.models.Student;
import com.StudentManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/students")
    public List<Student> getAllStudent(){
        return adminService.getAllStudent();
    }

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody Student student) {
        return adminService.addStudent(student);
    }

    @PostMapping("/add-course")
    public Course addCourse(@RequestBody Course course){
        return adminService.addCourse(course);
    }

    @PostMapping("/assign-course")
    public void assignCourse(@RequestParam Long studentId, @RequestParam Long courseId){
        adminService.assignCourseToStudent(studentId,courseId);
    }

    @GetMapping("/student/search")
    public List<Student> getStudentByName(@RequestParam String name){
        return adminService.findStudentsByName(name);
    }

    @GetMapping("/students/by-course")
    public List<Student> getStudentByCourse(@RequestParam Long courseId) {
        return adminService.findStudentsByCourseId(courseId);
    }
}
