package com.assignment.subscriber.subscriberassignment.service;

import com.assignment.subscriber.subscriberassignment.components.FileProcess;
import com.assignment.subscriber.subscriberassignment.model.Subscribers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeCache implements CommandLineRunner {

    private FileProcess fileOperationsComponent;

    public InitializeCache(FileProcess fileOperationsComponent) {
        this.fileOperationsComponent = fileOperationsComponent;
    }

    @Override
    public void run(String... args) throws Exception {
        Subscribers subscribersList = (Subscribers) fileOperationsComponent.readFromFile(Subscribers.class);
        if(subscribersList!=null) {

            fileOperationsComponent.getService().addAllCache(subscribersList);
        }

    }
}
