package bg.softuni.errors.web;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ObjectNotFoundAdvice {

    @ExceptionHandler({CategoryNotFoundException.class,ProductNotFoundException.class})
    public ModelAndView objectNotFoundException(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("object-not-found");
        return modelAndView;
    }


}
