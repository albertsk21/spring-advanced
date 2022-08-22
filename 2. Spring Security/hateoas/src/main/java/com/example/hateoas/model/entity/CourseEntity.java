package com.example.hateoas.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class CourseEntity extends BaseEntity {

    private String name;
    private BigDecimal price;
    private List<OrderEntity> orders = new ArrayList<>();

    public CourseEntity() {
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    public List<OrderEntity> getOrders() {
        return orders;
    }

    public CourseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public CourseEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CourseEntity setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }
}
