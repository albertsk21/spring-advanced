package com.example.pathfinderproject.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentCreateBM {
    public CommentCreateBM() {
    }

    private String message;

    @NotBlank
    @Size(min=10)
    public String getMessage() {
        return message;
    }

    public void setMessage(String textContent) {
        this.message = textContent;
    }
}
