package com.herookie.service.impl;

import com.herookie.entity.Student;
import com.herookie.repository.StudentRepository;
import com.herookie.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByName(String name) {
        Optional<Student> student = studentRepository.findByFirstName(name);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new RuntimeException("Student not found with name " + name);
        }
    }

    @Override
    public Student getStudentByRollNo(String rollNo) {
        Optional<Student> student = studentRepository.findByRoll(rollNo);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new RuntimeException("Student not found with roll number " + rollNo);
        }
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if (studentOptional.isPresent()) {
            Student existingStudent = studentOptional.get();
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setClasz(student.getClasz());
            existingStudent.setRoll(student.getRoll());
            return studentRepository.save(existingStudent);
        } else {
            throw new RuntimeException("Student not found with id " + student.getId());
        }
    }

    @Override
    public String updateStudentByName(String name, Student student) {
        Optional<Student> studentOptional = studentRepository.findByFirstName(name);
        if (studentOptional.isPresent()) {
            Student existingStudent = studentOptional.get();
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setClasz(student.getClasz());
            existingStudent.setRoll(student.getRoll());
            studentRepository.save(existingStudent);
            return "Student updated with name " + name;
        } else {
            throw new RuntimeException("Student not found with name " + name);
        }
    }

    @Override
    public void deleteStudentByName(String name) {
        Optional<Student> student = studentRepository.findByFirstName(name);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
        } else {
            throw new RuntimeException("Student not found with name " + name);
        }
    }

}