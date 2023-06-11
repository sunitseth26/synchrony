package com.synchrony.model.imgur;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDeleteResponse {

	private boolean data;
	private boolean success;
	private int status;
}
