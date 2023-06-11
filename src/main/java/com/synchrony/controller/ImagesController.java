package com.synchrony.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synchrony.client.ImgurClient;
import com.synchrony.constant.ApplicationConstants;
import com.synchrony.model.ImageApiRequest;
import com.synchrony.model.imgur.ImageDeleteResponse;
import com.synchrony.model.imgur.ImageGetResponse;
import com.synchrony.model.imgur.ImageRequest;
import com.synchrony.model.imgur.ImageUploadResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ImagesController {
	
	@Autowired
	private ImgurClient imgurClient;

	@PostMapping(value = "/image/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ImageUploadResponse> uploadImage(@ModelAttribute ImageApiRequest imageRequest) throws IOException {
		ImageRequest request = ImageRequest.builder().title(imageRequest.getTitle())
				.type(ApplicationConstants.FILE).description(imageRequest.getDescription())
				.name(imageRequest.getName())
				.build();
		if (!imageRequest.getImage().isEmpty()) {
            try {
                String fileName = imageRequest.getImage().getOriginalFilename();
                byte[] bytes = imageRequest.getImage().getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                buffStream.write(bytes);
                buffStream.close();
                request.setImage(new File(fileName));
            } catch (Exception e) {
                log.error("Unable to upload file", e);
            }
		}
		ImageUploadResponse response = imgurClient.uploadImage(request);
		if (response.getStatus() == 200) {
			log.info(ApplicationConstants.UPLOAD_IMAGE_SUCCESS);
			return new ResponseEntity<ImageUploadResponse>(response, HttpStatus.OK);
		}
		return new ResponseEntity<ImageUploadResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/image/{imageHash}")
	public ResponseEntity<ImageGetResponse> getImage(@PathVariable String imageHash) {
		
		ImageGetResponse response = imgurClient.getImageResponse(imageHash);
		
		return new ResponseEntity<ImageGetResponse>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/image/{imageDeleteHash}")
	public ResponseEntity<ImageDeleteResponse> deleteImage(@PathVariable String imageDeleteHash) {
		
		ImageDeleteResponse response = imgurClient.deleteImageResponse(imageDeleteHash);
		
		return new ResponseEntity<ImageDeleteResponse>(response, HttpStatus.OK);
	}
}
