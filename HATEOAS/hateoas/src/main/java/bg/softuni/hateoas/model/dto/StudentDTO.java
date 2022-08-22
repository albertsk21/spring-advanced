package bg.softuni.hateoas.model.dto;

import org.springframework.hateoas.server.core.Relation;

import java.util.ArrayList;
import java.util.List;

@Relation(collectionRelation = "students")
public class StudentDTO {
    private Long id;
    private String name;
    private int age;
    private boolean deleted;

    private List<OrderDTO> orders = new ArrayList<>();

    public StudentDTO() {
    }


    public List<OrderDTO> getOrders() {
        return orders;
    }

    public StudentDTO setOrders(List<OrderDTO> orders) {
        this.orders = orders;
        return this;
    }

    public Long getId() {
        return id;
    }



    public StudentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public StudentDTO setAge(int age) {
        this.age = age;
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
