package com.example.restservicebookapi.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class StudentDTO {


    private Long id;
    private String name;
    private int age;
    private List<CourseDTO> courses;
    private LocalDateTime created;
    private LocalDateTime modified;

    public StudentDTO() {
    }

    public LocalDateTime getModified() {
        return modified;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
