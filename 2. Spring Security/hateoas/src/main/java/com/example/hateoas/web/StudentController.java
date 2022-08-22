package com.example.hateoas.web;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.dto.StudentDTO;
import com.example.hateoas.service.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getAllStudents(){

        return ResponseEntity
                .ok(CollectionModel.of(this.studentService
                        .getAll()
                        .stream()
                        .map(s -> EntityModel.of(s,this.getStudentLinks(s)))
                        .collect(Collectors.toList())));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable(name = "id") Long id){
        var student = this.studentService.findById(id)
                .map( s -> EntityModel.of(s,this.getStudentLinks(s)));

        if (student.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(student.get());
    }
    @PostMapping("")
    public ResponseEntity<EntityModel<StudentDTO>> addStudent(@PathVariable(name = "id") Long id,
                                                              @RequestBody StudentDTO student){
        StudentDTO studentDTO = this.studentService.addStudent(student);
       var studentEntityModel = EntityModel.of(studentDTO,this.getStudentLinks(studentDTO));


       return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> editStudentById(@PathVariable(name = "id") Long id){
        throw new UnsupportedOperationException("not yet");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> deleteStudentById(@PathVariable(name = "id") Long id){
        throw new UnsupportedOperationException("not yet");
    }
    @GetMapping("/{id}/orders")
    public CollectionModel<OrderDTO> getStudentOrders(@PathVariable(name = "id") Long id){
        throw new UnsupportedOperationException("not yet");
    }

    private Link[] getStudentLinks(StudentDTO studentDTO){
        Link self = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(StudentController.class)
                        .getStudentById(studentDTO.getId())

        ).withSelfRel();

        Link orders = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(StudentController.class)
                        .getStudentOrders(studentDTO.getId())
        ).withRel("orders");


        return List.of(self,orders).toArray(new Link[0]);
    }

}
