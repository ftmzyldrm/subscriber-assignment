package com.assignment.subscriber.subscriberassignment.controller;

import com.assignment.subscriber.subscriberassignment.model.DeleteRequest;
import com.assignment.subscriber.subscriberassignment.model.Subscriber;
import com.assignment.subscriber.subscriberassignment.model.Subscribers;
import com.assignment.subscriber.subscriberassignment.service.SubscriberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class SubscriberController {

    private SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @RequestMapping(value = "/subscriber", method = POST)
    public ResponseEntity<Subscriber> saveSubscriber(@RequestBody Subscriber subscriber) {
        subscriberService.addToCache(subscriber);

        return ResponseEntity.ok(subscriber);

    }


    @RequestMapping(value = "/subscriber", method = PUT)
    public ResponseEntity<Subscriber> updateSubscriber(@RequestBody Subscriber subscriber) {

        subscriberService.updateCache(subscriber);
        return ResponseEntity.ok(subscriber);

    }

    @RequestMapping(value = "/subscriber", method = DELETE)
    public ResponseEntity<String> deleteSubscriber(@RequestBody DeleteRequest deleteRequest) {

        subscriberService.deleteCache(deleteRequest.getId());
        return ResponseEntity.ok("Subscriber is deleted successfully");

    }

    @RequestMapping(value = "/subscriber/getAllSubscribers", method = GET)
    public ResponseEntity<Subscribers> getAllSubscribers() {
        return ResponseEntity.ok(new Subscribers(new ArrayList<>(subscriberService.getCache().values())));
    }

    @RequestMapping(value = "/subscriber/getSubscriberById/{id}", method = GET)
    public ResponseEntity<Subscriber> getSubscriberById(@PathVariable("id") Long id) {
        Subscriber subscriber = subscriberService.getById(id);
        return ResponseEntity.ok(subscriber);
    }
}
