package com.assignment.subscriber.subscriberassignment.service;

import com.assignment.subscriber.subscriberassignment.model.Subscriber;
import com.assignment.subscriber.subscriberassignment.model.Subscribers;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImp implements SubscriberService {


    private HazelcastInstance hazelcastInstance;


    //constructor based injection is done
    public SubscriberServiceImp(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public void addAllCache(Subscribers subscribers) {

        subscribers.getSubscribers().forEach(s -> {
            getCache().put(s.getId(), s);
        });
    }

    @Override
    public IMap<Long, Subscriber> getCache() {

        return hazelcastInstance.getMap("subscribers");

    }

    @Override
    public void addToCache(Subscriber subscriber) {
        getCache().put(subscriber.getId(), subscriber);
    }

    @Override
    public void updateCache(Subscriber subscriber) {
        getCache().put(subscriber.getId(), subscriber);
    }
}
