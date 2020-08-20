package com.backbase.ubp.pb360.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentServicesQueryResponse {

    @JsonProperty("path")
    private String path;
    @JsonProperty("id")
    private String id;
    @JsonProperty("repositoryId")
    private String repositoryId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("relationships")
    private List<Object> relationships;
    @JsonProperty("links")
    private ContentServicesLink links;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Object> relationships) {
        this.relationships = relationships;
    }

    public ContentServicesLink getLinks() {
        return links;
    }

    public void setLinks(ContentServicesLink links) {
        this.links = links;
    }
}
