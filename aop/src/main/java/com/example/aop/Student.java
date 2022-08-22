package com.example.aop;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Student {

    public final Logger LOGGER = LoggerFactory.getLogger(Student.class);

    public void saySomething(){
        LOGGER.info("say Something");
    }

    public String returnSomething(){
        String res = "Hello world";
        LOGGER.info("Within returnSomething! Return: {}", res);
        return res;
    }

    public void echo(String whatToEcho){
        LOGGER.info("Within echo! To echo: {}", whatToEcho);
    }

    public String concat(String a, String b){
        LOGGER.info("Within concat! To concat: {}, {}",a,b);
        return a + b;
    }

    public void boom(){
        throw new NullPointerException("Something wrong happened");
    }

}
