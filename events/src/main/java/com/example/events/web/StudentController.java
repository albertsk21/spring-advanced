package com.example.events.web;

import com.example.events.dto.StudentDTO;
import com.example.events.ev.StudentCreateEvent;
import org.apache.coyote.Response;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    ApplicationEventPublisher applicationEventPublisher;


    public StudentController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping("")
    public ResponseEntity<StudentDTO> student(){


        StudentDTO student = new StudentDTO();
        student.setId(UUID.randomUUID().toString());
        student.setName("Christian");

        StudentCreateEvent event = new StudentCreateEvent(this,student);
        this.applicationEventPublisher.publishEvent(event);
        return ResponseEntity.ok(event.getStudent());
    }

}
