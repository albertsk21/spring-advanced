package com.example.hateoas.model.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    private CourseEntity course;
    private StudentEntity student;

    public OrderEntity() {
    }


    @ManyToOne
    @JoinColumn(name = "course_id")
    public CourseEntity getCourse() {
        return course;
    }

    @ManyToOne
    @JoinColumn(name = "student_id")
    public StudentEntity getStudent() {
        return student;
    }

    public OrderEntity setCourse(CourseEntity course) {
        this.course = course;
        return this;
    }

    public OrderEntity setStudent(StudentEntity student) {
        this.student = student;
        return this;
    }
}
