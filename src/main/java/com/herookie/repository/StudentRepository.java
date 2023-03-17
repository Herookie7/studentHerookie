package com.herookie.repository;

import com.herookie.entity.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);

    Student findByRollNo(String rollNo);

    Student findByNameAndUsername(String name, String username);

    Student findByRollNoAndUsername(String rollNo, String username);

    Optional<Student> findByFirstName(String firstName);

    Optional<Student> findByRoll(String rollNo);

}
