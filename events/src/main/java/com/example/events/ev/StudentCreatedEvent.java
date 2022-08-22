package com.example.events.ev;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentCreatedEvent {


    private final Logger LOGGER = LoggerFactory.getLogger(StudentCreatedEvent.class);

    @EventListener(StudentCreateEvent.class)
    public void handledEvent(StudentCreateEvent ev){
        this.LOGGER.info("Student has been created: {}",ev.getStudent().toString());

    }

}
