package com.amigoscode;

import org.apache.kafka.common.internals.Topic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "amigoscode",
            groupId = "groupId"
    )
    void listener(String data) {
        System.out.println("Listener recieved "+ data);
    }

}
