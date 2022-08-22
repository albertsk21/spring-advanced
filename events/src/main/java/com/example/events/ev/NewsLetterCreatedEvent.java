package com.example.events.ev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NewsLetterCreatedEvent implements ApplicationListener<StudentCreateEvent> {

    private final Logger LOGGER = LoggerFactory.getLogger(NewsLetterCreatedEvent.class);

    @Override
    public void onApplicationEvent(StudentCreateEvent event) {

        String message = String.format("Student %s with id: %s added to database for newsletter.",event.getStudent().getName(),event.getStudent().getId());
        LOGGER.info(message);

    }
}
