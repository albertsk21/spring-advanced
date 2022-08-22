package com.example.restservicebookapi.model.dto;

import java.time.LocalDateTime;

public class CourseDTO {


    private Long id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    public CourseDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
