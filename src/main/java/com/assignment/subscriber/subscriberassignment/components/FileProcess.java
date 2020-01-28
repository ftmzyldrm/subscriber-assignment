package com.assignment.subscriber.subscriberassignment.components;

import com.assignment.subscriber.subscriberassignment.model.Subscribers;
import com.assignment.subscriber.subscriberassignment.service.SubscriberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@Component
@Slf4j
public class FileProcess {

    @Value("${data.file.path}")
    private String filePath;

    private final ObjectMapper mapper;
    private SubscriberService service;

    public FileProcess(ObjectMapper mapper, SubscriberService service) {
        this.mapper = mapper;
        this.service = service;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public <T> Object readFromFile(Class<T> clazz) {

        T result = null;
        try {
            result = mapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            log.error(String.format("Invalid File Path : %s or Invalid class : %s ", filePath, clazz));
            log.error(e.getMessage());
        }

        return result;

    }

    private String pojoToJson(Object object) {
        String result = "";
        try {

            result = mapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            log.error(String.format("Invalid object: %s", object.getClass().getName()));
            log.error(e.getMessage());
        }

        return result;
    }

    @Scheduled(cron = "${data.update.scheduler}")
    public void writeToFile() {

        Subscribers subscribers = new Subscribers(new ArrayList<>(service.getCache().values()));

        try {
            Files.write(Paths.get(filePath), pojoToJson(subscribers).getBytes());
            log.info("the cache content is--> " + pojoToJson(subscribers));
            log.info(String.format("the cache content is written into file %s", filePath));

        } catch (IOException e) {
            log.error(String.format("The cache content cannot be written into the file %s", filePath));
            log.error(e.getMessage());
        }

    }
}
