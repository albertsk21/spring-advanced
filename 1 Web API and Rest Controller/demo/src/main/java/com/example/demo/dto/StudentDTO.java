package com.example.demo.dto;

import com.example.demo.error.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StudentDTO {

    private final Logger LOGGER = LoggerFactory.getLogger(StudentDTO.class);
    private String name;
    private String id;

    public void doSomething(){
        LOGGER.info("method: doSomething");
    }

    public String getName() {
        return name;
    }

    public StudentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public void generateException(){
        throw new StudentNotFoundException(id);
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}
