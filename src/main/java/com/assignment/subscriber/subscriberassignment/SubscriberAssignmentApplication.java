package com.assignment.subscriber.subscriberassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SubscriberAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriberAssignmentApplication.class, args);
    }

}
