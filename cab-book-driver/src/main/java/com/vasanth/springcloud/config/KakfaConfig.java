package com.vasanth.springcloud.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.vasanth.springcloud.constant.AppConstant;

@Configuration
public class KakfaConfig {

    @Bean
    NewTopic topic() {
		return TopicBuilder.name(AppConstant.CAB_LOCATION).build();
	}

}
