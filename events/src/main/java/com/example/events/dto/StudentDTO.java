package com.example.events.dto;

public class StudentDTO {
    private String id;
    private String name;


    public StudentDTO() {
    }

    public String getId() {
        return id;
    }

    public StudentDTO setId(String id) {
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


}
