package com.herookie.service;

import com.herookie.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentByName(String name);

    Student getStudentByRollNo(String rollNo);

    Student createStudent(Student student);

    Student updateStudent(Student student);

    String updateStudentByName(String name, Student student);

    void deleteStudentByName(String name);

}
