package bg.softuni.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {




    @GetMapping("/hello")
    public String hello(){




        String name = null;
        int sum = name.length() + 1;



        return "hello";
    }
}
