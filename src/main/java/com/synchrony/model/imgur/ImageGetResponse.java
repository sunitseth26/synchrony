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
public class ImageGetResponse {

   @JsonProperty("data")
   private ImageGetData data;

   @JsonProperty("success")
   private boolean success;

   @JsonProperty("status")
   private int status;
}