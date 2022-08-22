package com.example.demo.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryOrderCreatedEvent {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderCreatedEvent.class);

    @EventListener(OrderCreatedEvent.class)
    public void onOrderCreated(OrderCreatedEvent ev){
        LOGGER.info("Inventory corrected after order placed. {}",ev.getId());
    }
}
