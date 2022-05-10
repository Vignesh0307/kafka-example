package com.amigoscode.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    // bootstrapServers value is taken from the spring.kafka.bootstrap-servers in application.properties
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    //Now we need to have the configuration that can be passed to the producer factory
    @Bean
    public Map<String,Object>  producerConfig(){
        HashMap<String,Object> props = new HashMap<>();
        // In this props we are going to add some properties
        //The configuration is taken from the ProducerConfig class
        // In the ProducerConfig class we have bunch of configuration , In this we are going to add only these three
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);

        return props;
    }

    // Now we can create the producer factory which is responsible for creating the producer instances
    @Bean
    public ProducerFactory<String,String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    // Kafka templates sends the messages to the kafka topics
    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(ProducerFactory<String,String> producerFactory){
             return new KafkaTemplate<>(producerFactory);
    }
}















