package com.example.events.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BonusPointsOrderCreatedListener {


    private final Logger LOGGER = LoggerFactory.getLogger(BonusPointsOrderCreatedListener.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreated(OrderCreateEvent ev){
        LOGGER.info("Adding bonus points to the user. {}",ev.getOrderId());
    }
}
