package com.example.restservicebookapi.service.impl;

import com.example.restservicebookapi.model.dto.CourseDTO;
import com.example.restservicebookapi.model.entity.CourseEntity;
import com.example.restservicebookapi.repository.CourseRepository;
import com.example.restservicebookapi.repository.StudentRepository;
import com.example.restservicebookapi.service.CourseService;
import com.example.restservicebookapi.utils.error.CourseNotFoundException;
import com.example.restservicebookapi.utils.error.ObjectWithNameExistException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private ModelMapper modelMapper;
    public CourseServiceImpl(StudentRepository studentRepository,
                             CourseRepository courseRepository,
                             ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourseDTO> getAll() {
        List<CourseEntity> courses = this.courseRepository.findAll();
        List<CourseDTO> coursesDTO = new ArrayList<>();
        for (CourseEntity courseEntity : courses) {
            coursesDTO.add(this.modelMapper.map(courseEntity,CourseDTO.class));
        }
        return coursesDTO;
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {

        var courseOpt = this.courseRepository.findCourseEntityByName(courseDTO.getName());
        CourseEntity course = new CourseEntity();
        if (courseDTO.getId() != null) {
            courseOpt = this.courseRepository.findById(courseDTO.getId());
            if (courseOpt.isEmpty()) {
                throw new CourseNotFoundException(courseDTO.getId());
            }
            course = courseOpt.get();
            if (this.courseRepository.findCourseEntityByName(courseDTO.getName()).isEmpty()) {
                course.setName(courseDTO.getName());
            } else {
                throw new ObjectWithNameExistException(courseDTO.getName());
            }
            course.setModified(LocalDateTime.now());

        } else {
            courseOpt = this.courseRepository.findCourseEntityByName(courseDTO.getName());
            if (!courseOpt.isEmpty()) {
                throw new ObjectWithNameExistException(courseDTO.getName());
            }
            course.setCreated(LocalDateTime.now());
            course.setName(courseDTO.getName());
        }
        course =  this.courseRepository.save(course);
        return this.modelMapper.map(course,CourseDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        var course = this.courseRepository.findById(id);
        if(course.isEmpty()){
            throw new CourseNotFoundException(id);
        }
        CourseEntity courseEntity = course.get();
        courseEntity.getStudents().clear();
        this.courseRepository.save(courseEntity);

        this.courseRepository.deleteById(id);
    }

    @Override
    public CourseDTO findById(Long id) {
        var course = this.courseRepository.findById(id);
        if(course.isEmpty()){
            throw new CourseNotFoundException(id);
        }
        return this.modelMapper.map(course.get(),CourseDTO.class);
    }
}
