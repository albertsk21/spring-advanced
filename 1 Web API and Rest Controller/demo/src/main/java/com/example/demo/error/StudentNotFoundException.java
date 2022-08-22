package com.example.demo.error;


public class StudentNotFoundException extends RuntimeException {

    private String id;

    public StudentNotFoundException(String id) {
        super(String.format("Student with id %s dosen't exist", id));
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public StudentNotFoundException setId(String id) {
        this.id = id;
        return this;
    }
}
