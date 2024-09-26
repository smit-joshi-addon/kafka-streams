package com.kafka.streams.topology;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import com.kafka.streams.domain.Greeting;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GreetingStreamsTopology {

	public static String GREETINGS = "greetings";

	public static String GREETINGS_OUTPUT = "greetings-output";

//	@Autowired
//	private ObjectMapper mapper;

	@Autowired
	public void process(StreamsBuilder streamsBuilder) {
//		to work with Strings
//		KStream<String, String> greetingsStream = streamsBuilder.stream(GREETINGS,
//				Consumed.with(Serdes.String(), Serdes.String()));

//		KStream<String, String> modifiedStream = greetingsStream.mapValues((readOnlyKey, value) -> value.toUpperCase());

//		modifiedStream.print(Printed.<String, String>toSysOut().withLabel("modifiedStream"));

//		modifiedStream.to(GREETINGS_OUTPUT, Produced.with(Serdes.String(), Serdes.String()));

//		 to work with custom objects
		KStream<String, Greeting> greetingsStream = streamsBuilder.stream(GREETINGS,
				Consumed.with(Serdes.String(), new JsonSerde<>(Greeting.class)));

		KStream<String, Greeting> modifiedStream = greetingsStream
				.mapValues((readOnlyKey, value) -> new Greeting(value.message(), value.timestamp()));

		modifiedStream.print(Printed.<String, Greeting>toSysOut().withLabel("modifiedStream"));

		modifiedStream.to(GREETINGS_OUTPUT, Produced.with(Serdes.String(), new JsonSerde<>(Greeting.class)));
	}

}
