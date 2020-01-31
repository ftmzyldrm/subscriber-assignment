package com.assignment.subscriber.subscriberassignment.service;

import com.assignment.subscriber.subscriberassignment.model.Subscriber;
import com.assignment.subscriber.subscriberassignment.model.Subscribers;
import com.hazelcast.core.IMap;


public interface SubscriberService {
    void addAllCache(Subscribers subscribers);

    IMap<Long, Subscriber> getCache();

    void addToCache(Subscriber subscriber);

    void updateCache(Subscriber subscriber);

    void deleteCache(String request) ;

    Subscriber getById(Long id);

    void clearCache();
}
