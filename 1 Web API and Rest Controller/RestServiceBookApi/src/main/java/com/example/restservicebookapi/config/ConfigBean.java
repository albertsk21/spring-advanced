package com.example.restservicebookapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .version("1.0.0")
                        .title("Student Course Api")
                        .contact(new Contact().name("Albert Vasile"))
                );
    }

}
