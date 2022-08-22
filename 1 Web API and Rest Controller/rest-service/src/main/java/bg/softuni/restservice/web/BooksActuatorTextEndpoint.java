package bg.softuni.restservice.web;


import bg.softuni.restservice.repository.BookRepository;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(enableByDefault = false, id = "bookstext")
public class BooksActuatorTextEndpoint {
    private final BookRepository bookRepository;

    public BooksActuatorTextEndpoint(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @ReadOperation
    public String getBookCount(){
        return "books count = " + this.bookRepository.count();
    }

}
