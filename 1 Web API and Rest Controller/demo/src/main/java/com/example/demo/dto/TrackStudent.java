package com.example.demo.dto;

import com.example.demo.error.StudentNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;

@Aspect
@Configuration
public class TrackStudent {


    private final Logger LOGGER = LoggerFactory.getLogger(TrackStudent.class);

    @Pointcut("execution(* StudentDTO.doSomething(..))")
    public void track(){}


    @Before("track()")
    public void beforeMethodIsUsed(JoinPoint joinPoint){
        LOGGER.info("before method is used {}",joinPoint.getSignature().getName());
    }
    @After("track()")
    public void afterMethodIsUsed(){
        LOGGER.info("after method is used");
    }

    @AfterReturning(pointcut = "track()", returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        LOGGER.info("after returning method is used {}",((StudentDTO) result).getClass());
    }

    @AfterThrowing(pointcut = "execution(* StudentDTO.generateException())", throwing = "error")
    public void afterThrowing(StudentNotFoundException error){
        LOGGER.error("after method throw an error {}",error.getMessage() );
    }



    @Around("execution(* StudentDTO.getId())")
    public String interceptId(ProceedingJoinPoint pjp) throws Throwable {

        Object object = pjp.proceed();

        LOGGER.info(object.toString());
        return "id is intercepted successfully";
    }

}
