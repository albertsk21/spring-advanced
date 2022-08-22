package com.example.restservicebookapi.service.impl;

import com.example.restservicebookapi.model.dto.StudentDTO;
import com.example.restservicebookapi.model.entity.CourseEntity;
import com.example.restservicebookapi.model.entity.StudentEntity;
import com.example.restservicebookapi.repository.CourseRepository;
import com.example.restservicebookapi.repository.StudentRepository;
import com.example.restservicebookapi.service.StudentService;
import com.example.restservicebookapi.utils.error.ObjectNotFoundException;
import com.example.restservicebookapi.utils.error.StudentNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl  implements StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              CourseRepository courseRepository,
                              ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        var findStudent = this.studentRepository.findByName(studentDTO.getName());
        StudentEntity student = new StudentEntity();

        if(!findStudent.isEmpty()){
            student = findStudent.get();
            if(!studentDTO.getName().equals(student.getName()) && this.studentRepository.findByName(studentDTO.getName()).isEmpty() ){
                student.setName(studentDTO.getName());
            }
            student.setModified(LocalDateTime.now());
            student.setAge(studentDTO.getAge());
        }else{
            student.setName(studentDTO.getName());
            student.setAge(studentDTO.getAge());
            student.setCourses(new ArrayList<>());
            student.setCreated(LocalDateTime.now());
        }
        student = this.studentRepository.save(student);


        List<CourseEntity> courses = studentDTO
                .getCourses()
                .stream()
                .map( c -> {
                    CourseEntity course = this.modelMapper.map(c,CourseEntity.class);

                    if(this.courseRepository.findCourseEntityByName(c.getName()).isEmpty()){
                        course.setCreated(LocalDateTime.now());
                        course = this.courseRepository.save(course);
                        course.setStudents(new ArrayList<>());
                        return course;
                    }
                    course = this.courseRepository.findCourseEntityByName(c.getName()).get();
                    return course;
                }).collect(Collectors.toList());

        for (CourseEntity courseEntity : courses) {

            boolean studentIsNotEnrolled = courseEntity
                    .getStudents()
                    .stream()
                    .filter(s -> s.getName().equals(studentDTO.getName()))
                    .findAny().isEmpty();

            if(studentIsNotEnrolled){
                courseEntity.getStudents().add(student);
                this.courseRepository.save(courseEntity);
            }
         }

       var studentOpt = this.studentRepository.findByName(studentDTO.getName());
       if (studentOpt.isEmpty()){
            throw new StudentNotFoundException(studentDTO.getName());
       }

        return  this.modelMapper.map(studentOpt.get(),StudentDTO.class);
    }


    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentEntity> studentEntities = this.studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntities) {
            StudentDTO studentDTO = this.modelMapper.map(studentEntity, StudentDTO.class);
            studentDTOS.add(studentDTO);
            System.out.println();
        }
        return studentDTOS;
    }

    @Override
    public void deleteById(Long id) {


        var studentOpt = this.studentRepository.findById(id);
        if(studentOpt.isEmpty()){
            throw new ObjectNotFoundException("Student with this id:" + id + "not exists");
        }

        StudentEntity student = this.studentRepository.findById(id).get();
        String studentName = student.getName();
        List<CourseEntity> courses = new ArrayList<>(student.getCourses());

        for (CourseEntity course : courses) {
            long counter = course.getStudents().stream().filter(s -> s.getName().equals(studentName)).count();
            if(counter > 0){
                course.getStudents().remove(student);
                this.courseRepository.save(course);

            }
        }
        student.getCourses().clear();
        student =  this.studentRepository.save(student);
        this.studentRepository.delete(student);
    }

    @Override
    public Optional<StudentDTO> findStudentById(Long id) {

        var studentOpt = this.studentRepository.findById(id);
        if(studentOpt.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(this.modelMapper.map(studentOpt.get(),StudentDTO.class));

    }
}
