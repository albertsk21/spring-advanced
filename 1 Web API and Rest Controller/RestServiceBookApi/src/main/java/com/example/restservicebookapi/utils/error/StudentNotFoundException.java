package com.example.restservicebookapi.utils.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Student not found")
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String name) {
        super("Student with name " + name + " not found");
    }
}
