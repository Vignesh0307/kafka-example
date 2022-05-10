package com.amigoscode.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    // bootstrapServers value is taken from the spring.kafka.bootstrap-servers in application.properties
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    //Now we need to have the configuration that can be passed to the producer factory
    @Bean
    public Map<String,Object> consumerConfig(){
        HashMap<String,Object> props = new HashMap<>();
        // In this props we are going to add some properties
        //The configuration is taken from the ProducerConfig clasSS
// In the ProducerConfig class we have bunch of configuration , In this we are going to add only these three
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringSerializer.class);

        return props;
    }

    // Now we can create the producer factory which is responsible for creating the producer instances
    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    // Now I need to specify the listener container factory
    // This listener container factory will recieve all the messages from all the topics or partition in a single thread

    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> factory(
            ConsumerFactory<String,String> consumerFactory
    ){
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
