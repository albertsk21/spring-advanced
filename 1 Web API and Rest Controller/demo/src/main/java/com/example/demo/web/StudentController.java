package com.example.demo.web;

import com.example.demo.dto.StudentDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StudentController {


    public CollectionModel<EntityModel<StudentDTO>> get(){

        StudentDTO studentDTO = new StudentDTO();
        EntityModel<StudentDTO> model = EntityModel.of(studentDTO);
        List<EntityModel<StudentDTO>> list = new ArrayList<>(List.of(model));
        CollectionModel<EntityModel<StudentDTO>> collectionModel = CollectionModel.of(list);


        return collectionModel;
    }

    @GetMapping("/{id}")
    private ResponseEntity<EntityModel<StudentDTO>> getStudent(@PathVariable("id")String id){


        StudentDTO studentDTO = new StudentDTO();
        var studentModel = EntityModel.of(studentDTO,this.getStudentLinks(studentDTO));
        return ResponseEntity.ok(studentModel);
    }

    private Link[] getStudentLinks(StudentDTO student) {
        Link self = linkTo(methodOn(StudentController.class)
                .getStudent(student.getId()))
        .withSelfRel();

        return new Link[]{self};
    }

}
