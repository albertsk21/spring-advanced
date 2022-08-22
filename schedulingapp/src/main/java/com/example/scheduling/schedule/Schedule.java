package com.example.scheduling.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Schedule {

    private final Logger LOGGER = LoggerFactory.getLogger(Schedule.class);

    public Schedule() {
    }

//    @Scheduled(cron = "*/5 * * * * *")
    public void cron() throws InterruptedException {
        this.LOGGER.info("Cron Schedule each 5 seconds past");
    }

//    @Scheduled(fixedRate = 5000)
    public void showTimeWithFixedRate() {
        System.out.println(LocalDateTime.now());
    }
    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void showTimeWithFixedDelay() {
        System.out.println(LocalDateTime.now());
    }
}
