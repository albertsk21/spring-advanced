package com.example.pathfinderproject.model.service;

public class CommentServiceModel {
    private Long id;
    private String message;
    private String creator;

    public CommentServiceModel() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
