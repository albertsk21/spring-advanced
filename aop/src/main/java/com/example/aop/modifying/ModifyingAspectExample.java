package com.example.aop.modifying;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(
        value = "examples.modifying.enabled",
        havingValue = "true"
)
public class ModifyingAspectExample {



    private final Logger LOGGER = LoggerFactory.getLogger(ModifyingAspectExample.class);


    @Pointcut("execution(* com.example.aop.Student.concat(..))")
    public void concatPoint(){
        LOGGER.info("concat is called inside Pointcut!");
    }

    @Around( value = "concatPoint() && args(a, b)")
    public String onConcatCalled(@NotNull ProceedingJoinPoint pjp, String a, String b) throws Throwable {
        var modifiedArgA = "(" + a + ")";
        var modifiedArgB = "(" + b + ")";

        var result = pjp.proceed(new Object[]{modifiedArgA,modifiedArgB});
        return "[" + result + "]";
    }

}
