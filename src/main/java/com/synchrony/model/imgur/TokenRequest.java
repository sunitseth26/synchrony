package com.synchrony.model.imgur;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRequest {

	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("client_id")
	private String clientId;
	@JsonProperty("client_secret")
	private String clientSecret;
	@JsonProperty("grant_type")
	private String grantType;
}
