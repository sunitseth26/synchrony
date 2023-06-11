package com.synchrony.model;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageApiRequest {
	private MultipartFile image;
	private String type;
	private String name;
	private String title;
	private String description;
}
