package com.example.aop.modifying;

import com.example.aop.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        value = "examples.modifying.enabled",
        havingValue = "true"
)
public class ModifyingExample implements CommandLineRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(ModifyingExample.class);

    private final Student student;

    public ModifyingExample(Student student) {
        this.student = student;
    }

    @Override
    public void run(String... args) throws Exception {

        var arg1 = "a";
        var arg2= "b";
        var result = student.concat(arg1,arg2);
        LOGGER.info("Method was called with args {} and {}. The result is {}", arg1,arg2,result);
    }
}
