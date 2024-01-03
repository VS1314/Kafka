package com.example.demo.contoller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.TransactionMessage;
import com.example.demo.services.KafkaProducerService;

import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
public class EventController {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@PostMapping("/event")
	ResponseEntity<String> event(@RequestBody TransactionMessage transactionMessage) {
		UUID uuid = UUID.randomUUID();
		kafkaProducerService.send("transaction-topic", uuid, transactionMessage);
		return ResponseEntity.ok("Sent");
	}

}
