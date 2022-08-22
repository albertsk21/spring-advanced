package bg.softuni.hateoas.web;

import bg.softuni.hateoas.model.dto.OrderDTO;
import bg.softuni.hateoas.model.dto.StudentDTO;
import bg.softuni.hateoas.service.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/students")
@RestController
public class StudentController {


    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getAllStudents(){


        var students = this.studentService
                .findAllStudents()
                .stream()
                .map(s ->EntityModel.of(s, createStudentLinks(s)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(students)
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable(name = "id") Long id){
        Optional<StudentDTO> student = this.studentService.findStudentById(id);

        if(student.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var studentDto = student.get();
        return ResponseEntity.ok(
                EntityModel.of(studentDto,createStudentLinks(studentDto))
        );
    }

    @PostMapping("/{id}")
    public EntityModel<StudentDTO> updateStudent(@PathVariable(name = "id") Long id, StudentDTO studentDTO){
        throw new UnsupportedOperationException("Cooming soon");
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getStudentOrders(@PathVariable(name = "id") Long id){


        Optional<StudentDTO> studentDTOOpt = this.studentService.findStudentById(id);


        if(studentDTOOpt.isEmpty()){
            return ResponseEntity
                    .notFound()
                    .build();
        }



        var studentDTO = studentDTOOpt.get();
        var ordersDTO = studentDTO
                .getOrders()
                .stream()
                .map(o -> EntityModel.of(o,createOrderLinks(o)))
                .collect(Collectors.toList());



        return ResponseEntity.ok(
                CollectionModel.of(ordersDTO)
        );
    }

    private Link[] createOrderLinks(OrderDTO orderDTO){

        List<Link> result = new ArrayList<>();


        Link self = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(StudentController.class)
                        .getStudentById(orderDTO.getStudentId()))
                .withRel("student");




        result.add(self);
        return result.toArray(new Link[0]);
    }

    private Link[] createStudentLinks(StudentDTO studentDTO){
        //self
        //update
        //orders

        List<Link> result = new ArrayList<>();

        Link self = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(StudentController.class)
                        .getStudentById(studentDTO.getId()))
                .withSelfRel();



        Link orders = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(StudentController.class)
                        .getStudentOrders(studentDTO.getId()))
                .withRel("orders");

        result.addAll(List.of(self,orders));

        if(!studentDTO.isDeleted()) {

            Link update = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(StudentController.class)
                            .updateStudent(studentDTO.getId(), studentDTO))
                    .withRel("update");
            result.add(update);
        }

        return result.toArray(new Link[0]);
    }

}
