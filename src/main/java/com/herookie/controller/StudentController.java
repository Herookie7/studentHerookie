package com.herookie.controller;

import com.herookie.entity.Student;
import com.herookie.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the student portal!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();
        String message = "Logged in successfully as " + role;
        return ResponseEntity.ok(message);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<?> getStudentByName(@PathVariable String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();

        if (role.equals("STUDENT")) {
            Student student = studentService.getStudentByName(name);
            return ResponseEntity.ok(student);
        } else {
            Student student = studentService.getStudentByName(name);
            return ResponseEntity.ok(student);
        }
    }

    @GetMapping("/{rollNo}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<?> getStudentByRollNo(@PathVariable String rollNo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();

        if (role.equals("STUDENT")) {
            Student student = studentService.getStudentByRollNo(rollNo);
            return ResponseEntity.ok(student);
        } else {
            Student student = studentService.getStudentByRollNo(rollNo);
            return ResponseEntity.ok(student);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStudentByName(@PathVariable String name) {
        studentService.deleteStudentByName(name);
        return ResponseEntity.noContent().build();
    }

}