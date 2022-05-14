package com.booklibrary.technical.cache;

import com.booklibrary.domain.Book;
import com.booklibrary.technical.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@Slf4j
public class InMemoryCacheTests {
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private InMemoryCache inMemoryCache;


    @Test
    public void check_adding_to_cache() throws ExecutionException {
        Book book = new Book("name", "author", 5);

        given(bookRepository.findByBookName(book.getBookName())).willReturn(Optional.of(book));

        Book cachedBook = inMemoryCache.cache.get(book.getBookName());

        Assertions.assertNotNull(cachedBook);
        Assertions.assertEquals(book, cachedBook);

        Map<String, Book> cacheMap = inMemoryCache.cache.asMap();

        Assertions.assertTrue(cacheMap.containsKey(book.getBookName()));
    }
}
