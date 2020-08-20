package com.backbase.ubp.pb360.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentServicesQueryRequest {

    @JsonProperty("paths")
    private List<String> paths;

    public ContentServicesQueryRequest withPaths(List<String> paths) {
        this.paths = paths;
        return this;
    }

    public ContentServicesQueryRequest withPath(String path) {
        List<String> pathList = new ArrayList<>();
        pathList.add(path);
        this.paths = pathList;
        return this;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }
}
