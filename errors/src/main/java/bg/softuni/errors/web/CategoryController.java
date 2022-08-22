package bg.softuni.errors.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable("id") String id){
        throw new CategoryNotFoundException(id);
    }



//    @ExceptionHandler(CategoryNotFoundException.class)
    public ModelAndView onCategoryNotFound(CategoryNotFoundException ex){
        ModelAndView modelAndView = new ModelAndView("category-not-found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        modelAndView.addObject("categoryId",ex.getCategoryId());

        return modelAndView;
    }
}
