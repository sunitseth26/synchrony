package com.synchrony.model.imgur;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdConfig {

   @JsonProperty("safeFlags")
   private List<String> safeFlags;

   @JsonProperty("highRiskFlags")
   private List<String> highRiskFlags;

   @JsonProperty("unsafeFlags")
   private List<String> unsafeFlags;

   @JsonProperty("wallUnsafeFlags")
   private List<String> wallUnsafeFlags;

   @JsonProperty("showsAds")
   private boolean showsAds;

   @JsonProperty("showAdLevel")
   private int showAdLevel;

   @JsonProperty("safe_flags")
   private List<String> safeFlags1;

   @JsonProperty("high_risk_flags")
   private List<String> highRiskFlags1;

   @JsonProperty("unsafe_flags")
   private List<String> unsafeFlags1;

   @JsonProperty("wall_unsafe_flags")
   List<String> wallUnsafeFlags1;

   @JsonProperty("show_ads")
   private boolean showAds;

   @JsonProperty("show_ad_level")
   private int showAdLevel1;

   @JsonProperty("nsfw_score")
   private int nsfwScore;


}