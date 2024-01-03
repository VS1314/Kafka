package com.example.demo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.models.TransactionMessage;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumerService {

	@KafkaListener(topics = "transaction-topic", groupId = "group-id")
	public void consume(TransactionMessage transactionMessage) {
		System.out.println("We received the transaction with the id: " + transactionMessage.getTransactionId()
				+ ". The status is: " + transactionMessage.getStatus());
	}

}
