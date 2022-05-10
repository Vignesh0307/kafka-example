package com.amigoscode;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageRecord {

    private String message;
}
