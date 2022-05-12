package com.appdomeinterview.booklibrary.application;

import com.appdomeinterview.booklibrary.api.books.bookdtos.BookDto;
import com.appdomeinterview.booklibrary.api.books.bookdtos.BookErrors;
import com.appdomeinterview.booklibrary.api.books.bookdtos.BookMappers;
import com.appdomeinterview.booklibrary.domain.Book;
import com.appdomeinterview.booklibrary.technical.repositories.BookRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BookService {

    final BookRepository bookRepository;

    final BookMappers bookMappers;

    LoadingCache<String, Book> inMemoryBookCache =
            CacheBuilder.newBuilder()
                        .expireAfterWrite(1, TimeUnit.MINUTES)
                        .maximumSize(3)
                        .build(
                                new CacheLoader<String, Book>() {
                                    @Override
                                    public Book load(String key) {
                                        return bookRepository.findByBookName(key)
                                                             .orElseThrow(() -> new BookErrors.BookDoesNotExistException(key));
                                    }
                                }
                              );

    public BookService(@Autowired BookRepository bookRepository, @Autowired BookMappers bookMappers) {
        this.bookRepository = bookRepository;
        this.bookMappers = bookMappers;
    }

    @SneakyThrows
    public Book getBookByName(String bookName) {
        return inMemoryBookCache.get(bookName);
    }


    public Book addBook(BookDto bookDto) {
        val domainBook = bookMappers.map(bookDto);

        bookRepository.save(domainBook);
        return domainBook;
    }

    public void deleteBookByName(String bookName) {
        bookRepository.deleteBookByBookName(bookName);
    }

}
