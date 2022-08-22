package com.example.restservicebookapi.web;

import com.example.restservicebookapi.model.dto.StudentDTO;
import com.example.restservicebookapi.model.entity.StudentEntity;
import com.example.restservicebookapi.service.StudentService;
import com.example.restservicebookapi.utils.error.ObjectNotFoundException;
import com.example.restservicebookapi.utils.error.StudentNotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/app/students")
public class StudentRestController {

    private StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Parameter(name = "200", description = "you will get all the students")
    @GetMapping("")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
       return ResponseEntity.ok(this.studentService.getAllStudent());
    }

    @Parameter(name = "201",description = "you can create a student is not exists a course the program, add the course automatically")
    @PostMapping("")
    public ResponseEntity<StudentDTO> studentPOST(@RequestBody StudentDTO student){
         this.studentService.saveStudent(student);
        return ResponseEntity.created(URI.create("/api/v1/app/students")).build();
    }
    @Tag(name = "204",description = "If Student exist will return de status 204 no content and get the object")
    @Tag(name = "404",description = "If Student exist will return de status 404 not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDTO> deleteStudentById(@PathVariable(name = "id") Long id){

        this.studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @Parameter(name = "id",description = " you need Id Student to replace the object")
    @Tag(name = "200",description = "If Student exist will return de status 200 ok and get the object")
    @Tag(name = "404",description = "If Student exist will return de status 404 not found")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name = "id") Long id){
        var student = this.studentService.findStudentById(id);
        if(student.isEmpty()){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student.get());
    }

    @Parameter(name = "id",description = " you need Id Student to replace the object")
    @Tag(name = "200",description = "If Student exist will return de status 201 ok and replace the object")
    @Tag(name = "404",description = "If Student dosen't exist will return de status 404 not found")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudentById(@PathVariable(name = "id") Long id, @RequestBody StudentDTO studentDTO){
        studentDTO.setId(id);
        studentDTO = this.studentService.saveStudent(studentDTO);
        return ResponseEntity.ok(studentDTO);
    }



}
