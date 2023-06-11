package com.synchrony.client;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.synchrony.model.imgur.ImageDeleteResponse;
import com.synchrony.model.imgur.ImageGetResponse;
import com.synchrony.model.imgur.ImageRequest;
import com.synchrony.model.imgur.ImageUploadResponse;
import com.synchrony.model.imgur.TokenDetails;
import com.synchrony.model.imgur.TokenRequest;

@Component
public class ImgurClient {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${imgur.api.url}")
	private String imgurApiUrl;
	
	@Value("${imgur.api.oauth2.token.url}")
	private String imgurTokenUrl;
	
	@Value("${imgur.api.clientId}")
	private String imgurApiClientId;
	
	@Value("${imgur.api.clientSecret}")
	private String imgurApiClientSecret;
	
	@Value("${imgur.api.refreshToken}")
	private String imgurApiRefreshToken;
	
	@Value("${imgur.api.image.upload.url}")
	private String imgurApiImageUploadUrl;
	
	@Value("${imgur.api.image.url}")
	private String imgurApiImageUrl;
	
	public TokenDetails getAccessToken() {
		TokenRequest request = TokenRequest.builder().clientId(imgurApiClientId).clientSecret(imgurApiClientSecret)
				.refreshToken(imgurApiRefreshToken).grantType("refresh_token").build();
		ResponseEntity<TokenDetails> response = restTemplate.postForEntity(imgurApiUrl + imgurTokenUrl, request, TokenDetails.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return response.getBody();
		} else {
			return null;
		}
	}
	
	public ImageUploadResponse uploadImage(ImageRequest request) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set("Authorization", "Client-ID " + imgurApiClientId); 
		
		MultiValueMap<String, Object> body  = new LinkedMultiValueMap<>();
		body.add("image", encodeFileToBase64Binary(request.getImage().getAbsolutePath()));
		body.add("type", "base64");
		body.add("name", request.getName());
		body.add("title", request.getTitle());
		body.add("description", request.getDescription());
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		
		ResponseEntity<ImageUploadResponse> response = restTemplate.postForEntity(imgurApiUrl + imgurApiImageUploadUrl, requestEntity, ImageUploadResponse.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return response.getBody();
		} else {
			return null;
		}
	}
	
	public ImageGetResponse getImageResponse(String imageHash) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Client-ID " + imgurApiClientId);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<ImageGetResponse> response = restTemplate.exchange(imgurApiUrl + imgurApiImageUrl + imageHash, HttpMethod.GET, entity, ImageGetResponse.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return response.getBody();
		} else {
			return null;
		}
	}
	
	public ImageDeleteResponse deleteImageResponse(String imageDeleteHash) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Client-ID " + imgurApiClientId);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<ImageDeleteResponse> response = restTemplate.exchange(imgurApiUrl + imgurApiImageUrl + imageDeleteHash, HttpMethod.DELETE, entity, ImageDeleteResponse.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return response.getBody();
		} else {
			return null;
		}
	}
	
	private static String encodeFileToBase64Binary(String fileName)
	        throws IOException {

	    File file = new File(fileName);
	    byte[] bytes = Files.readAllBytes(file.toPath());
	    byte[] encoded = Base64.getEncoder().encode(bytes);
	    String encodedString = new String(encoded,StandardCharsets.US_ASCII);

	    return encodedString;
	}
}
