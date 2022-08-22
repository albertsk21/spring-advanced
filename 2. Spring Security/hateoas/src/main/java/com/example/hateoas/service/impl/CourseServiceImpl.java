package com.example.hateoas.service.impl;

import com.example.hateoas.repository.StudentRepository;
import com.example.hateoas.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private StudentRepository studentRepository;

    public CourseServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
