package com.synchrony.model.imgur;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageData {
	public String id;
    public String title;
    public String description;
    public int datetime;
    public String type;
    public boolean animated;
    public int width;
    public int height;
    public int size;
    public int views;
    public int bandwidth;
    public String vote;
    public boolean favorite;
    public String nsfw;
    public String section;
    public String account_url;
    public int account_id;
    public boolean is_ad;
    public boolean in_most_viral;
    public List<String> tags;
    public int ad_type;
    public String ad_url;
    public boolean in_gallery;
    public String deletehash;
    public String name;
    public String link;
}
