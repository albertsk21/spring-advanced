package com.example.springsecurityapplication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/students")
public class StudentManagementController {

    private final List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1,"Damian"),
            new Student(2,"Alexandrov"),
            new Student(3,"Christian")
    ));

    public StudentManagementController() {
    }



    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(this.students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id){
        Student student = this.students
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst().orElse(null);

        return ResponseEntity.ok(student);

    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")

    public void deleteStudentById(@PathVariable("studentId") int studentId){
        System.out.println(studentId);

    }
    @PutMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId")   int studentId, @RequestBody  Student student){
        System.out.println(studentId);
        System.out.println(student);

    }
}
