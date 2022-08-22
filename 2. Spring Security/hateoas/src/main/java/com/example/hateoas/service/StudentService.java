package com.example.hateoas.service;

import com.example.hateoas.model.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {


    Optional<StudentDTO> findById(Long id);
    List<StudentDTO> getAll();
    StudentDTO addStudent(StudentDTO studentDTO);
}
