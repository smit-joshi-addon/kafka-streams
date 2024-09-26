package com.kafka.streams.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.kafka.streams.topology.GreetingStreamsTopology;

@Configuration
public class GreetingsStreamsConfig {

//	@Bean
//	ObjectMapper objectMapper() {
//		return new ObjectMapper().registerModule(new JavaTimeModule())
//				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//	}

	@Bean
	NewTopic greetingsTopic() {
		return TopicBuilder.name(GreetingStreamsTopology.GREETINGS).partitions(2).replicas(1).build();
	}

	@Bean
	NewTopic greetingsOutputTopic() {
		return TopicBuilder.name(GreetingStreamsTopology.GREETINGS_OUTPUT).partitions(2).replicas(1).build();
	}

}
