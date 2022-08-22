package com.example.demo.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

public class OrderCreatedEvent extends ApplicationEvent {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderCreatedEvent.class);
    private String id;
    public OrderCreatedEvent(Object source, String id) {
        super(source);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
