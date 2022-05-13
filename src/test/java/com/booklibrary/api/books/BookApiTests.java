package com.booklibrary.api.books;

import com.booklibrary.api.books.bookdtos.BookDto;
import com.booklibrary.api.books.bookdtos.BookMappers;
import com.booklibrary.domain.Book;
import com.booklibrary.technical.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class BookApiTests {

    @Autowired
    JacksonTester<BookDto> jacksonBookTester;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private BookMappers bookMappers;

    @Test
    public void get_book_endpoint() throws Exception {
        Book book = new Book("name", "author", 5);

        //Given
        given(bookRepository.findByBookName(book.getBookName())).willReturn(Optional.of(book));

        //When
        MockHttpServletResponse response = mockMvc.perform(get(String.format("%s/%s",
                                                                             BooksApi.BASE_PATH,
                                                                             book.getBookName()))
                                                                   .accept(MediaType.APPLICATION_JSON))
                                                  .andReturn()
                                                  .getResponse();
        //Then
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(response.getContentAsString(), jacksonBookTester.write(bookMappers.map(book)).getJson());

    }

    @Test
    public void add_book_endpoint() throws Exception {
        BookDto newBook = new BookDto("name", "author", 5);

        //Given
        Book domainBook = bookMappers.map(newBook);
        given(bookRepository.save(domainBook)).willReturn(domainBook);

        //When
        MockHttpServletResponse response = mockMvc.perform(post(String.format("%s/", BooksApi.BASE_PATH))
                                                                   .contentType(MediaType.APPLICATION_JSON)
                                                                   .content(jacksonBookTester.write(newBook).getJson()))
                                                  .andReturn()
                                                  .getResponse();
        //Then
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(newBook.getBookName(), response.getContentAsString());
    }
}
