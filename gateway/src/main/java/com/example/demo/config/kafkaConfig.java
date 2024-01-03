package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.demo.models.TransactionMessage;

@Configuration
public class kafkaConfig {

	@Bean
	NewTopic newTopic() {
		return TopicBuilder.name("transaction-topic").partitions(1).replicas(1).build();
	}

	@Bean
	ProducerFactory<UUID, TransactionMessage> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	KafkaTemplate<UUID, TransactionMessage> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	/*
	 * @Bean KafkaAdmin.NewTopics topics(){ return new NewTopics(
	 * TopicBuilder.name("topic1") .build(), TopicBuilder.name("topic2")
	 * .replicas(1) .build(), TopicBuilder.name("topic3") .partitions(1) .build() );
	 * }
	 */

}
