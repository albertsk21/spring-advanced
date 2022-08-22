package com.example.errorapi.web;

import com.example.errorapi.error.ObjectNotFound;
import com.example.errorapi.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/studentss")
public class StudentNotRest {

    private final List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(Long.parseLong("1"), "Christian"),
            new Student(Long.parseLong("2"), "Alexander"),
            new Student(Long.parseLong("3"), "Jessica")
,            new Student(Long.parseLong("4"), "Josh")
    ));


    @GetMapping("")
    public String getAllStudents(Model model){
        model.addAttribute("students",this.students);
        return "students";
    }


    @GetMapping("/{id}")
    public String getStudentById(@PathVariable(name = "id") Long id){
        throw new ObjectNotFound(String.format("Student with id: %d not found",id));
    }
}
