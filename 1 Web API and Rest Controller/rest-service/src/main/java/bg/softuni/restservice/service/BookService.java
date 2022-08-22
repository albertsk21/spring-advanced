package bg.softuni.restservice.service;

import bg.softuni.restservice.model.dto.BookDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface BookService {

  List<BookDTO> getAllBooks();

  Optional<BookDTO> findBookById(long id);

  Page<BookDTO> getBooks(int pageNo, int pageSize, String sortBy);

  void deleteBookById(long id);

  Optional<Long> updateBook(BookDTO bookToUpdate);

  long createBook(BookDTO bookToCreate);
}
