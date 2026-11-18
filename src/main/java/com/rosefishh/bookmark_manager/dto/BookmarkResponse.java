package com.rosefishh.bookmark_manager.dto;

import java.time.LocalDateTime;

public class BookmarkResponse {

    private Long id;
    private String title;
    private String url;
    private String description;
    private LocalDateTime createdAt;

    public BookmarkResponse() {
    }

    public BookmarkResponse(Long id, String title, String url, String description, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.createdAt = createdAt;
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
}
