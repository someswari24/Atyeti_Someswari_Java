package com.smartHomeAutomationSystem.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
//import org.springframework.kafka.support.serializer.ErrorDeserializationHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public static final String DEVICE_STATUS_TOPIC = "device-status";
    public static final String TEMPERATURE_SENSOR_TOPIC = "temperature-sensor";
    public static final String SECURITY_ALERTS_TOPIC = "security-alerts";
    public static final String DEVICE_COMMANDS_TOPIC = "device-commands";
    public static final String DEAD_LETTER_TOPIC = "dead-letter-topic";

    @Bean
    public NewTopic deviceStatusTopic() {
        return TopicBuilder.name(DEVICE_STATUS_TOPIC).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic temperatureSensorTopic() {
        return TopicBuilder.name(TEMPERATURE_SENSOR_TOPIC).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic securityAlertsTopic() {
        return TopicBuilder.name(SECURITY_ALERTS_TOPIC).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic deviceCommandsTopic() {
        return TopicBuilder.name(DEVICE_COMMANDS_TOPIC).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic deadLetterTopic() {
        return TopicBuilder.name(DEAD_LETTER_TOPIC).partitions(1).replicas(1).build();
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.smartHomeAutomationSystem.event");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sihas-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return props;
    }
    
    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(Object.class, false)
        );
    }
}