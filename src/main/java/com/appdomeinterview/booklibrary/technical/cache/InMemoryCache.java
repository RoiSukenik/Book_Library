package com.appdomeinterview.booklibrary.technical.cache;

import com.appdomeinterview.booklibrary.api.books.bookdtos.BookErrors;
import com.appdomeinterview.booklibrary.domain.Book;
import com.appdomeinterview.booklibrary.technical.repositories.BookRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class InMemoryCache {

    private final BookRepository bookRepository;
    //Those constants can and should be moved to application properties.
    private final Integer TIME_UNTIL_CACHE_VALUE_EXPIRE = 1;

    private final Integer CACHE_SIZE = 3;

    public LoadingCache<String, Book> cache =
            CacheBuilder.newBuilder()
                        .expireAfterWrite(TIME_UNTIL_CACHE_VALUE_EXPIRE, TimeUnit.MINUTES)
                        .maximumSize(CACHE_SIZE)
                        .build(
                                new CacheLoader<String, Book>() {
                                    @Override
                                    public Book load(String key) {
                                        return bookRepository.findByBookName(key)
                                                             .orElseThrow(() -> new BookErrors.BookDoesNotExistException(key));
                                    }
                                }
                              );

    public InMemoryCache(@Autowired BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}

