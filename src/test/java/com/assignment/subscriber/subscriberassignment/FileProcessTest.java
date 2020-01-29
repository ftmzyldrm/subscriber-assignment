package com.assignment.subscriber.subscriberassignment;


import com.assignment.subscriber.subscriberassignment.components.FileProcess;
import com.assignment.subscriber.subscriberassignment.model.Subscriber;
import com.assignment.subscriber.subscriberassignment.model.Subscribers;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;


@SpringBootTest
public class FileProcessTest {

    @Autowired
    FileProcess fileProcess;


    @Test
    void should_read_the_file_path_from_application_properties_file() {

        String actualFilePath = fileProcess.getFilePath();
        assertThat(actualFilePath).isEqualTo("/home/stats/StudyFolder/subscriber-assignment/data/data.json");
    }

    @Test
    void should_read_content_from_file_then_map_to_pojo() {

        //given
        Subscribers expectedList = getMockSubscribersList();

        //when
        Subscribers subscribers = readSubscribers();

        assertThat(subscribers.getSubscribers()).containsAll(expectedList.getSubscribers());
    }


    @Test
    void should_update_file_content_accordingto_cron_expression() {

        //given
        Subscribers subscribers = readSubscribers();

        await().atMost(Duration.TWO_MINUTES).untilAsserted(() -> assertThat(subscribers).isNotEqualTo(readSubscribers()));


    }

    private Subscribers readSubscribers() {
        return (Subscribers) fileProcess.readFromFile(Subscribers.class);
    }


    private Subscribers getMockSubscribersList() {
        Subscribers mockSubscribers = new Subscribers();

        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(new Subscriber(1L, "Stephan King", "905552551122"));
        subscribers.add(new Subscriber(2L, "Alice Gracy", "905552551133"));
        subscribers.add(new Subscriber(3L, "Glory Wisdom", "905552551144"));

        mockSubscribers.setSubscribers(subscribers);
        return mockSubscribers;
    }


}
