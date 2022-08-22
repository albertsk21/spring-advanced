package bg.softuni.restservice.web;

import bg.softuni.restservice.model.dto.BooksStatDTO;
import bg.softuni.restservice.repository.BookRepository;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@Component
@RestControllerEndpoint(id ="booksrest")
public class BooksActuatorRestEndpoint {


    private BookRepository bookRepository;

    public BooksActuatorRestEndpoint(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping
    public BooksStatDTO test(){
        return new BooksStatDTO().setBooksCnt(this.bookRepository.count());
    }
}
