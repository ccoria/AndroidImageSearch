package com.codepath.imagesearch;

/**
 * Created by ccoria on 2/1/15.
 */
public class ImageModel {

    private String url;
    private String thumbnail_url;
    private String title;

    public ImageModel(String url, String thumbnail_url, String title) {
        this.url = url;
        this.thumbnail_url = thumbnail_url;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public String getUrl() {
        return url;
    }

}
