package com.example.springsecurityapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {
    @GetMapping("/home")
    public String home(){
        return "index";
    }
}
