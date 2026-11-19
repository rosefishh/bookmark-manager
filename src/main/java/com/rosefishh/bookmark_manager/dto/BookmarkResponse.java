package com.rosefishh.bookmark_manager.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookmarkResponse {

    private Long id;
    private String title;
    private String url;
    private String description;
    private LocalDateTime createdAt;
    private List<String> tags;

    public BookmarkResponse() {
    }

    public BookmarkResponse(Long id, String title, String url, String description, LocalDateTime createdAt, List<String> tags) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.createdAt = createdAt;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
