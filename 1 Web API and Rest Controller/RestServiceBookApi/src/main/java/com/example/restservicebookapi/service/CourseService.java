package com.example.restservicebookapi.service;


import com.example.restservicebookapi.model.dto.CourseDTO;
import com.example.restservicebookapi.model.entity.CourseEntity;

import java.util.List;

public interface CourseService {


    List<CourseDTO> getAll();
    CourseDTO saveCourse(CourseDTO courseDTO);
    void deleteById(Long id);
    CourseDTO findById(Long id);
}
