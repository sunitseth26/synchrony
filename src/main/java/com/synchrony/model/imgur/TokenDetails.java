package com.synchrony.model.imgur;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDetails {

	private String access_token;
	@JsonProperty("expires_in")
	private long expiresIn;
	@JsonProperty("token_type")
	private String tokenType;
	private String scope;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("account_id")
	private long accountId;
	@JsonProperty("account_username")
	private String accountUsername;
}
