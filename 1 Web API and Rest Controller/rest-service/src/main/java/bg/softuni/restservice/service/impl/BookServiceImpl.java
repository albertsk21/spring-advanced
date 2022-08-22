package bg.softuni.restservice.service.impl;

import bg.softuni.restservice.model.dto.AuthorDTO;
import bg.softuni.restservice.model.dto.BookDTO;
import bg.softuni.restservice.model.entity.AuthorEntity;
import bg.softuni.restservice.model.entity.BookEntity;
import bg.softuni.restservice.repository.AuthorRepository;
import bg.softuni.restservice.repository.BookRepository;
import bg.softuni.restservice.service.BookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final ModelMapper modelMapper;

  public BookServiceImpl(BookRepository bookRepository,
      AuthorRepository authorRepository,
      ModelMapper modelMapper) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<BookDTO> getAllBooks() {
    return bookRepository.
        findAll().
        stream().
        map(this::asDto).
        collect(Collectors.toList());
  }

  @Override
  public Optional<BookDTO> findBookById(long id) {
    return bookRepository.
        findById(id).
        map(this::asDto);
  }

  @Override
  public Page<BookDTO> getBooks(int pageNo, int pageSize, String sortBy) {
    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    return bookRepository.
        findAll(pageable).
        map(this::asDto);
  }

  @Override
  public void deleteBookById(long bookId) {
    bookRepository.deleteById(bookId);
  }

  @Override
  public Optional<Long> updateBook(BookDTO bookToUpdate) {
    Optional<BookEntity> bookOpt =
        bookRepository.findById(bookToUpdate.getId());

    if (bookOpt.isEmpty()) {
      return Optional.empty();
    }

    var authorName = bookToUpdate.getAuthor().getName();

    AuthorEntity author = authorRepository.
        findByName(authorName).
        orElseGet(() -> {
          var newAuthor = new AuthorEntity().setName(authorName);
          return authorRepository.save(newAuthor);
        });

    var updatedBook = bookOpt.
        get().
        setAuthor(author).
        setIsbn(bookToUpdate.getIsbn()).
        setTitle(bookToUpdate.getTitle());

    return Optional.of(bookRepository.save(updatedBook).getId());
  }

  @Override
  public long createBook(BookDTO bookToCreate) {

    var authorName = bookToCreate.getAuthor().getName();

    AuthorEntity author = authorRepository.
        findByName(authorName).
        orElseGet(() -> new AuthorEntity().setName(authorName));

    authorRepository.save(author);

    BookEntity newBook = new BookEntity().
        setAuthor(author).
        setIsbn(bookToCreate.getIsbn()).
        setTitle(bookToCreate.getTitle());

    return bookRepository.save(newBook).getId();
  }

  private BookDTO asDto(BookEntity bookEntity) {
    BookDTO bookDTO = modelMapper.map(bookEntity, BookDTO.class);
    AuthorDTO authorDTO = modelMapper.map(bookEntity.getAuthor(), AuthorDTO.class);

    return bookDTO.
        setAuthor(authorDTO);
  }
}
