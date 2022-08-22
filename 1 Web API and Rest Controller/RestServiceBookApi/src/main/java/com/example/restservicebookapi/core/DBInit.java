package com.example.restservicebookapi.core;


import com.example.restservicebookapi.model.entity.CourseEntity;
import com.example.restservicebookapi.model.entity.StudentEntity;
import com.example.restservicebookapi.repository.CourseRepository;
import com.example.restservicebookapi.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

//@Component
public class DBInit implements CommandLineRunner {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public DBInit(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public void run(String... args){


        if(this.courseRepository.findAll().isEmpty()){

            CourseEntity javaScript = new CourseEntity();
            javaScript.setName("JavaScript");
            javaScript.setCreated(LocalDateTime.now());

            CourseEntity javaBasics = new CourseEntity();
            javaBasics.setName("Java Basics");
            javaBasics.setCreated(LocalDateTime.now());

            CourseEntity javaFundamentals = new CourseEntity();
            javaFundamentals.setName("Java Fundamentals");
            javaFundamentals.setCreated(LocalDateTime.now());

            this.courseRepository.saveAll(List.of(javaScript, javaBasics, javaFundamentals));

        }


        if(this.studentRepository.findAll().isEmpty()){
//            StudentEntity peter = new StudentEntity();
//            peter.setAge(20);
//            peter.setName("Peter");
//            peter.setCreated(LocalDateTime.now());

            StudentEntity alexander = new StudentEntity();
            alexander.setAge(34);
            alexander.setName("Alexander");
            alexander.setCreated(LocalDateTime.now());

            StudentEntity peter = new StudentEntity();
            peter.setAge(35);
            peter.setName("Peter");
            peter.setCreated(LocalDateTime.now());

            CourseEntity javaScript = this.courseRepository.findCourseEntityByName("JavaScript").get();
            CourseEntity javaFundamentals = this.courseRepository.findCourseEntityByName("Java Fundamentals").get();
            CourseEntity javaBasics = this.courseRepository.findCourseEntityByName("Java Basics").get();
            alexander = this.studentRepository.save(alexander);
            peter = this.studentRepository.save(peter);


            javaBasics.getStudents().add(peter);
            javaFundamentals.getStudents().add(peter);
            javaScript.getStudents().add(peter);
            javaBasics.getStudents().add(alexander);
            javaFundamentals.getStudents().add(alexander);
            javaScript.getStudents().add(alexander);

//            this.courseRepository.saveAll(List.of(javaBasics,javaFundamentals,javaScript));


            alexander.setCourses(List.of(javaBasics,javaFundamentals,javaScript));
            peter.setCourses(List.of(javaBasics,javaFundamentals,javaScript));

            this.studentRepository.saveAll(List.of(peter,alexander));

        }


    }
}
