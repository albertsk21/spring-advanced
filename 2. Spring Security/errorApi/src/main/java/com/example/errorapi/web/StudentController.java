package com.example.errorapi.web;

import com.example.errorapi.error.ObjectNotFound;
import com.example.errorapi.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(Long.parseLong("1"), "Christian"),
            new Student(Long.parseLong("2"), "Alexander"),
            new Student(Long.parseLong("3"), "Jessica")
    ));


    @GetMapping("")
    public ResponseEntity<ResponseModel<List<Student>>> getAllStudents(){
        return ResponseEntity.ok(this.students);
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable(name = "id") Long id){
        throw new ObjectNotFound(String.format("Student with id: %d not found",id));
    }


}
