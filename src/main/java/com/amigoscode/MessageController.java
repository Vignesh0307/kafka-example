package com.amigoscode;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping
    public void publish(@RequestBody MessageRecord request){
         kafkaTemplate.send("amigoscode",request.getMessage());
    }

}
