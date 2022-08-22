package com.example.aop.basic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(
        value = "examples.basic.enabled",
        havingValue = "true"
)
public class BasicAspectExample {

    private final Logger LOGGER = LoggerFactory.getLogger(BasicAspectExample.class);


    @Pointcut("execution(* com.example.aop.Student.*(..))")
    public void onAllStudentMethods(){

    }
    @Pointcut("execution(* com.example.aop.Student.echo(..))")
    public void onEchoCalled(){
    }

    @After("onEchoCalled()")
    public void afterEcho(){
        LOGGER.info("After echo has been called");
    }

    @Before("onAllStudentMethods()")
    public void beforeEachMethods(JoinPoint joinPoint){
        LOGGER.info("Before method {} is triggered with arg {}",
                joinPoint.getSignature(),
                joinPoint.getArgs());
    }


    @AfterReturning(value = "onAllStudentMethods()", returning = "returnedValue")

    public void afterReturning(
            JoinPoint joinPoint,
            Object returnedValue){

        LOGGER.info("Method {} returned {}",joinPoint.getSignature(),returnedValue);
    }

    @AfterThrowing(value = "onAllStudentMethods()",throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Exception error){

        LOGGER.info("Method {} throwed exception with message {}", joinPoint.getSignature(),error.getMessage());
    }
}
