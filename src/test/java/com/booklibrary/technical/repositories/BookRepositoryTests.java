package com.booklibrary.technical.repositories;

import com.booklibrary.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class BookRepositoryTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void should_create_new_book() {
        Book book = new Book("name", "author", 5);
        bookRepository.save(book);

        Book bookByName = bookRepository.findByBookName(book.getBookName()).orElse(null);
        Assertions.assertNotNull(bookByName);
        Assertions.assertEquals(book.getBookName(), bookByName.getBookName());
    }

    @Test
    public void should_delete_book() {
        Book book = new Book("name", "author", 5);
        bookRepository.save(book);
        bookRepository.deleteBookByBookName(book.getBookName());
        Assertions.assertNull(bookRepository.findByBookName(book.getBookName()).orElse(null));
    }
}
