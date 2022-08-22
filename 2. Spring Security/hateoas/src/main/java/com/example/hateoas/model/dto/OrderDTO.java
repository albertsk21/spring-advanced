package com.example.hateoas.model.dto;

public class OrderDTO {

    private Long id;
    private long courseId;
    private long studentId;

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public OrderDTO setId(Long id) {
        this.id = id;
        return this;
    }


    public long getCourseId() {
        return courseId;
    }

    public OrderDTO setCourseId(long courseId) {
        this.courseId = courseId;
        return this;
    }

    public long getStudentId() {
        return studentId;
    }

    public OrderDTO setStudentId(long studentId) {
        this.studentId = studentId;
        return this;
    }
}
