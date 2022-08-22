package com.example.restservicebookapi.utils.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Object with this name already exist")
public class ObjectWithNameExistException extends RuntimeException {

    public ObjectWithNameExistException(String name) {
        super("Object with this name " + name + " already exist");
    }
}
