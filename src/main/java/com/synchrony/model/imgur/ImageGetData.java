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
public class ImageGetData {

   @JsonProperty("id")
   private String id;

   @JsonProperty("title")
   private String title;

   @JsonProperty("description")
   private String description;

   @JsonProperty("datetime")
   private int datetime;

   @JsonProperty("type")
   private String type;

   @JsonProperty("animated")
   private boolean animated;

   @JsonProperty("width")
   private int width;

   @JsonProperty("height")
   private int height;

   @JsonProperty("size")
   private int size;

   @JsonProperty("views")
   private int views;

   @JsonProperty("bandwidth")
   private int bandwidth;

   @JsonProperty("vote")
   private String vote;

   @JsonProperty("favorite")
   private boolean favorite;

   @JsonProperty("nsfw")
   private boolean nsfw;

   @JsonProperty("section")
   private String section;

   @JsonProperty("account_url")
   private String accountUrl;

   @JsonProperty("account_id")
   private String accountId;

   @JsonProperty("is_ad")
   private boolean isAd;

   @JsonProperty("in_most_viral")
   private boolean inMostViral;

   @JsonProperty("has_sound")
   private boolean hasSound;

   @JsonProperty("tags")
   private List<String> tags;

   @JsonProperty("ad_type")
   private int adType;

   @JsonProperty("ad_url")
   private String adUrl;

   @JsonProperty("edited")
   private String edited;

   @JsonProperty("in_gallery")
   private boolean inGallery;

   @JsonProperty("link")
   private String link;

   @JsonProperty("ad_config")
   private AdConfig adConfig;

}