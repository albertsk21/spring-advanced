package com.example.events.web;

import com.example.events.events.OrderCreateEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderController {


    private final ApplicationEventPublisher eventPublisher;

    public OrderController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    @GetMapping("/create")
    public String createOrder(){
//        crate order here
        OrderCreateEvent evt = new OrderCreateEvent(this, UUID.randomUUID().toString());

        eventPublisher.publishEvent(evt);
        return "done";
    }
}