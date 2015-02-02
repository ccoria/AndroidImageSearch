package com.codepath.imagesearch;

/**
 * Created by ccoria on 2/1/15.
 */
public class ImageModel {

    private String url;
    private String thumbnail_url;

    public ImageModel(String url, String thumbnail_url) {
        this.url = url;
        this.thumbnail_url = thumbnail_url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public String getUrl() {
        return url;
    }

}
