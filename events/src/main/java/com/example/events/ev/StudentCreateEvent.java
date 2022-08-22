package com.example.events.ev;

import com.example.events.dto.StudentDTO;
import org.springframework.context.ApplicationEvent;

public class StudentCreateEvent extends ApplicationEvent {

    private StudentDTO student;


    public StudentCreateEvent(Object source, StudentDTO studentDTO) {
        super(source);
        this.student = studentDTO;
    }


    public StudentDTO getStudent() {
        return student;
    }
}
