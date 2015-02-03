package com.codepath.imagesearch;

import java.io.Serializable;

/**
 * Created by ccoria on 2/2/15.
 */
public class ImageFiltersModel  implements Serializable {

    private String size;
    private String color;
    private String type;
    private String site;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
