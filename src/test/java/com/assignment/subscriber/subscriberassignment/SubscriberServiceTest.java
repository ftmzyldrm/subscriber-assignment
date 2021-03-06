package com.assignment.subscriber.subscriberassignment;


import com.assignment.subscriber.subscriberassignment.components.FileProcess;
import com.assignment.subscriber.subscriberassignment.model.DeleteRequest;
import com.assignment.subscriber.subscriberassignment.model.Subscriber;
import com.assignment.subscriber.subscriberassignment.model.Subscribers;
import com.assignment.subscriber.subscriberassignment.service.SubscriberService;
import com.assignment.subscriber.subscriberassignment.exception.SubscriberNotFoundException;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SubscriberServiceTest {

    @Autowired
    private FileProcess fileProcess;

    @Autowired
    private SubscriberService subscriberService;


    @Test
    void should_add_file_content_into_cache() {

        //given:
        //read subsribers from file
        Subscribers subscribers = readSubscribers();

        //when:
        subscriberService.addAllCache(subscribers);

        //then:
        assertThat(subscriberService.getCache()).isNotEmpty();
        assertThat(subscriberService.getCache().values()).contains(new Subscriber(1L, "Stephan King", "905552551122"));

    }

    @Test
    void should_add_a_subscriber_into_cache() {

        //given
        Subscriber subscriber = new Subscriber(4L, "Kobe Byrant", "904342323523");

        //when
        subscriberService.addToCache(subscriber);

        //then:
        assertThat(subscriberService.getCache().values()).contains(subscriber);
    }


    @Test
    void should_update_a_subscriber_of_cache() {

        //given:
        Subscriber subscriber = new Subscriber(5L, "Amy Winehouse", "140w324324234");

        subscriberService.addToCache(subscriber);

        //when:
        Subscriber updatedSubscriber = new Subscriber(5L, "Billie Ellish", "0254254435435");
        subscriberService.updateCache(updatedSubscriber);

        assertThat(subscriberService.getCache().values()).contains(updatedSubscriber);

    }


    @Test
    void should_update_file_content_accordingto_cron_expression() {

        //given:
        Subscribers subscribers = readSubscribers();

        await().atMost(Duration.TWO_MINUTES).untilAsserted(() -> assertThat(subscribers).isNotEqualTo(readSubscribers()));


    }

    @Test
    void should_delete_subscriber_from_cache_ifexists() {

        //given:
        Subscriber subscriber = new Subscriber(7L, "will be deleted", "983-409583-40985");

        subscriberService.addToCache(subscriber);

        DeleteRequest deleteRequest = new DeleteRequest(7L);
        //when:
        subscriberService.deleteCache(deleteRequest.getId());

        assertThat(subscriberService.getCache().values()).doesNotContain(subscriber);


    }

    @Test
    void should_throw_subscriber_notfound_exception_if_subscriber_is_not_in_cache() {

        //given:
        Subscriber subscriber = new Subscriber(7L, "will be deleted", "983-409583-40985");

        subscriberService.addToCache(subscriber);

        DeleteRequest deleteRequest = new DeleteRequest(7L);
        //when:
        subscriberService.deleteCache(deleteRequest.getId());

        assertThat(subscriberService.getCache().values()).doesNotContain(subscriber);

        SubscriberNotFoundException thrown = assertThrows(SubscriberNotFoundException.class, () -> subscriberService.deleteCache(deleteRequest.getId()), "deleteCache expected to throw but it didint");

        assertTrue(thrown.getMessage().contains("Subscriber Not Found"));
    }


    @Test
    void should_get_subscriber_by_id_from_cache() {

        //given:
        Subscriber subscriber = new Subscriber(8L, "will be searched", "3248-923-73294");
        subscriberService.addToCache(subscriber);


        //when
        Subscriber actualSubcriber = subscriberService.getById(subscriber.getId());

        assertThat(actualSubcriber).isEqualTo(subscriber);


    }

    private Subscribers readSubscribers() {
        return (Subscribers) fileProcess.readFromFile(Subscribers.class);
    }
}
