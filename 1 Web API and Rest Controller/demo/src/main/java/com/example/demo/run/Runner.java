package com.example.demo.run;

import com.example.demo.events.OrderCreatedEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Runner implements CommandLineRunner {

    private final ApplicationEventPublisher applicationEventPublisher;

    public Runner(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public void run(String... args) throws Exception {

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(this, UUID.randomUUID().toString());

        applicationEventPublisher.publishEvent(orderCreatedEvent);
    }
}
