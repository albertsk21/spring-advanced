package com.example.events.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
public class RequestHandledListener {
    private final Logger LOGGER = LoggerFactory.getLogger(RequestHandledListener.class);

    @EventListener(ServletRequestHandledEvent.class)
    public void onRequestProcessed(ServletRequestHandledEvent evt){


        LOGGER.info("Processed request to URL {}",evt.getRequestUrl());    }

}
