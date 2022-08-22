package com.example.events.events;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryOrderCreatedListener {


    private final Logger LOGGER = LoggerFactory.getLogger(InventoryOrderCreatedListener.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreated(OrderCreateEvent ev){
        LOGGER.info("Inventory corrected after order placed. {}",ev.getOrderId());
    }

}
