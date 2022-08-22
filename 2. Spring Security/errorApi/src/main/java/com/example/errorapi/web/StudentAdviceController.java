package com.example.errorapi.web;

import com.example.errorapi.error.ObjectNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@RequestMapping("/students")
public class StudentAdviceController {

//    @ExceptionHandler(ObjectNotFound.class)
    public ModelAndView handleErrors(ObjectNotFound ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage",ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        modelAndView.setViewName("my-error-page");
        return modelAndView;
    }

}
