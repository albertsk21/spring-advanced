package com.example.restservicebookapi.service;

import com.example.restservicebookapi.model.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDTO saveStudent(StudentDTO studentDTO);
    List<StudentDTO> getAllStudent();
    void deleteById(Long id);
    Optional<StudentDTO> findStudentById(Long id);
}
