package bg.softuni.restservice.web;


import bg.softuni.restservice.model.entity.AuthorEntity;
import bg.softuni.restservice.model.entity.BookEntity;
import bg.softuni.restservice.repository.AuthorRepository;
import bg.softuni.restservice.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BooksControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    private AuthorEntity testAuthor;
    private BookEntity testBook1;
    private BookEntity testBook2;

    @BeforeEach
    void setUp(){
        this.bookRepository.deleteAll();

        testAuthor = new AuthorEntity().setName("Joshua Bloch");
        testAuthor = authorRepository.save(testAuthor);

        testBook1 = new BookEntity().
                setTitle("Effective Java").
                setIsbn("978-0134685991").
                setAuthor(testAuthor);

        testBook2 = new BookEntity().
                setTitle("Java™ Puzzlers: Traps, Pitfalls, and Corner Cases").
                setIsbn("978-0321336781").
                setAuthor(testAuthor);


        testAuthor.setBooks(List.of(testBook1, testBook2));
        bookRepository.saveAll(List.of(testBook1, testBook2));
    }



    @AfterEach
    void tearDown(){
        this.bookRepository.deleteAll();
    }


    @Test
    void testGetAllBooks() throws Exception {

        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$.[0].title",is(this.testBook1.getTitle())))
                .andExpect(jsonPath("$.[0].author.name",is(this.testBook1.getAuthor().getName())))
                .andExpect(jsonPath("$.[1].title",is(this.testBook2.getTitle())))
                .andExpect(jsonPath("$.[1].author.name",is(this.testBook2.getAuthor().getName())));
    }

    @Test
    void testDeleteBookById() throws Exception{

        this.mockMvc.perform(delete("/api/books/{id}",this.testBook2.getId()))
                .andExpect(status().isNoContent());
        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testCreateBook() throws Exception {
//        Arrange

        String isbnNameTest = "testIsbn";
        String bookTitleTest = "Test";
        String bookAuthorTest = "Tester";

        String formatBookJson = "{\n" +
                "        \"title\": \"%s\",\n" +
                "        \"isbn\": \"%s\",\n" +
                "        \"author\": {\n" +
                "            \"name\": \"%s\"\n" +
                "        }\n" +
                "    }";


        String bookJsonTest = String.format(formatBookJson,bookTitleTest,isbnNameTest,bookAuthorTest);

        this.mockMvc.perform(
                post("/api/books").
                        contentType(MediaType.APPLICATION_JSON).
                        content(bookJsonTest))
                .andExpect(header().string("Location", containsString("/api/books")))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$.[2].author.name",is(bookAuthorTest)))
                .andExpect(jsonPath("$.[2].title",is(bookTitleTest)));

    }

}