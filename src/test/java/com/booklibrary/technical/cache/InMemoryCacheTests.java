package com.booklibrary.technical.cache;

import com.booklibrary.domain.Book;
import com.booklibrary.technical.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class InMemoryCacheTests {
    @Autowired
    private BookRepository bookRepository;

    private InMemoryCache inMemoryCache = new InMemoryCache(bookRepository);


    @Test
    public void check_adding_to_cache() throws ExecutionException {
        Book book = new Book("name", "author", 5);

        bookRepository.save(book);

        inMemoryCache.cache.get(book.getBookName());

        Map<String, Book> cacheMap = inMemoryCache.cache.asMap();

        Assertions.assertTrue(cacheMap.containsKey(book.getBookName()));
    }
}
