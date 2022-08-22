package com.example.restservicebookapi.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "student")
public class StudentEntity extends BaseEntity{

    private String name;
    private int age;
    private LocalDateTime created;
    private List<CourseEntity> courses;
    private LocalDateTime modified;


    public StudentEntity() {
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

    @Column(unique = true)
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

    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER,   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }
}
