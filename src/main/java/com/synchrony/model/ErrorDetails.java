package com.synchrony.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetails {

	private int statusCode;
	private String message;
	private LocalDateTime timestamp;
}
