package com.example.demo.services;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.models.TransactionMessage;

@Service
public class KafkaProducerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

	@Autowired
	KafkaTemplate<UUID, TransactionMessage> kafkaTemplate;

	public void send(String topicName, UUID key, TransactionMessage transactionMessage) {
		var future = kafkaTemplate.send(topicName, key, transactionMessage);
		future.whenComplete((SendResult, exception) -> {
			if (exception!=null) {
				LOGGER.error(exception.getMessage());
				future.completeExceptionally(exception);
			} else {
				future.complete(SendResult);
			}
			LOGGER.info("The Id is: " + transactionMessage.getTransactionId() 
				+ "Transaction status to kafka topic: " + transactionMessage.getStatus());
		});
	}

}
