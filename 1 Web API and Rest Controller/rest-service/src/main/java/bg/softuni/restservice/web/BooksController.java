package bg.softuni.restservice.web;

import bg.softuni.restservice.model.dto.BookDTO;
import bg.softuni.restservice.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RequestMapping("/api/books")
@RestController
public class BooksController {

  private final BookService bookService;

  public BooksController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<BookDTO>> getAllBooks() {
    List<BookDTO> allBooks = bookService.getAllBooks();

    return ResponseEntity.
        ok(allBooks);
  }

  @GetMapping("/pageable")
  public ResponseEntity<Page<BookDTO>> getAllBooks(
      @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
      @RequestParam(name = "pageSize", defaultValue = "3") int pageSize,
      @RequestParam(name = "sortBy", defaultValue = "id") String sortBy

  ) {
    Page<BookDTO> booksPage =
        bookService.getBooks(pageNo, pageSize, sortBy);

    return ResponseEntity.ok(booksPage);
  }


  @Parameter(name = "id", description = "The Id Of the book")
  @Tag(name = "Find book by id", description = "Find book details by given book Id")
  @ApiResponse(responseCode = "404",  description = "For Book not found")
  @ApiResponse(responseCode = "200",  description = "Book is found")
  @GetMapping("/{id}")
  public ResponseEntity<BookDTO> getBookById(@PathVariable("id") long id) {
    Optional<BookDTO> bookDtoOpt = bookService.findBookById(id);

    if (bookDtoOpt.isEmpty()) {
      return ResponseEntity.
          notFound().
          build();
    } else {
      return ResponseEntity.
          ok(bookDtoOpt.get());
    }
  }
  @Tag(name = "Delete book by id", description = "You can delete a book by give book Id")
  @DeleteMapping("/{id}")
  public ResponseEntity<BookDTO> deleteBookById(@PathVariable("id") long id) {
    bookService.deleteBookById(id);

    return ResponseEntity.
        noContent().
        build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDTO> updateBook(@PathVariable("id") long bookId,
      @RequestBody BookDTO bookDTO) {

    bookDTO.setId(bookId);

    Optional<Long> updatedBookId = bookService.updateBook(bookDTO);

    return updatedBookId.isEmpty() ?
        ResponseEntity.notFound().build() :
        ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<BookDTO> createBook(
      @RequestBody BookDTO bookDTO,
      UriComponentsBuilder uriBuilder) {
    long bookId = bookService.createBook(bookDTO);

    URI location = uriBuilder.
        path("/api/books/{id}").
        buildAndExpand(bookId).
        toUri();

    return ResponseEntity.
        created(location).
        build();
  }
}
