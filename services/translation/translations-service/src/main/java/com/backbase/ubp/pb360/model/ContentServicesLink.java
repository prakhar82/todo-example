package com.backbase.ubp.pb360.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentServicesLink {

    @JsonProperty("content-by-id")
    private HrefLink contentById;
    @JsonProperty("content-by-path")
    private HrefLink contentByPath;

    public HrefLink getContentById() {
        return contentById;
    }

    public void setContentById(HrefLink contentById) {
        this.contentById = contentById;
    }

    public HrefLink getContentByPath() {
        return contentByPath;
    }

    public void setContentByPath(HrefLink contentByPath) {
        this.contentByPath = contentByPath;
    }
}
