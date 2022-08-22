package com.example.hateoas.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class StudentEntity extends BaseEntity {

    private int age;
    private String name;
    private boolean deleted;
    private List<OrderEntity> orders = new ArrayList<>();

    public StudentEntity() {
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    public List<OrderEntity> getOrders() {
        return orders;
    }

    public StudentEntity setName(String name) {
        this.name = name;
        return this;
    }

    public StudentEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public StudentEntity setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }

    public StudentEntity setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
