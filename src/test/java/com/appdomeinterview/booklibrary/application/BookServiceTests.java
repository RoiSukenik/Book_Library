package com.appdomeinterview.booklibrary.application;

import com.appdomeinterview.booklibrary.api.books.bookdtos.BookDto;
import com.appdomeinterview.booklibrary.domain.Book;
import com.appdomeinterview.booklibrary.technical.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookServiceTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Test
    public void add_book() {
        BookDto newBook = BookDto.builder()
                                 .bookName("name")
                                 .bookAuthor("author")
                                 .numberOfPages(5)
                                 .build();

        bookService.addBook(newBook);

        Book newDomainBook = bookRepository.findByBookName("name");

        Assertions.assertNotNull(newDomainBook);
        Assertions.assertEquals(newBook.getBookName(), newDomainBook.getBookName());
        Assertions.assertEquals(newBook.getBookAuthor(), newDomainBook.getBookAuthor());
        Assertions.assertEquals(newBook.getNumberOfPages(), newDomainBook.getNumberOfPages());
    }

    @Test
    public void get_book_by_name() {
        Book book = new Book("name", "author", 5);
        bookRepository.save(book);

        Book domainBook = bookService.getBookByName(book.getBookName());
        Assertions.assertNotNull(domainBook);
    }

    @Test
    public void delete_book_by_name() {
        Book book = new Book("name", "author", 5);
        bookRepository.save(book);

        bookService.deleteBookByName("name");

        Assertions.assertNull(bookRepository.findByBookName("name"));
    }

}
