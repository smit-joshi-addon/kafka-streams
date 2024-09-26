package com.kafka.streams.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Greeting(@JsonProperty String message, @JsonProperty LocalDateTime timestamp) {
}
