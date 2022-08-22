package com.example.springsecurityapplication;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>(Arrays.asList(
       new Student(1,"Damian"),
       new Student(2,"Alexandrov"),
       new Student(3,"Christian")
    ));


    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(this.students);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        Student student = this.students
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst().orElse(null);

        return ResponseEntity.ok(student);

    }




}
