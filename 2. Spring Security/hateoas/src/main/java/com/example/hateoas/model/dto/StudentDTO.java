package com.example.hateoas.model.dto;


import java.util.List;

public class StudentDTO {

    private Long id;
    private int age;
    private String name;
    private boolean deleted;
    private List<OrderDTO> orders;

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public StudentDTO setOrders(List<OrderDTO> orders) {
        this.orders = orders;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public StudentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public StudentDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public StudentDTO setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

}
