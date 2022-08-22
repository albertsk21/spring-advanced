package bg.softuni.errors.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/products")
@Controller
public class ProductController {


    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") String id){
//        we could not find the product
        throw new ProductNotFoundException("Product with this id: " + id + "dosen't exist");
    }

}
