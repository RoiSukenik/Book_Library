package com.appdomeinterview.booklibrary.application;

import com.appdomeinterview.booklibrary.api.books.bookdtos.BookDto;
import com.appdomeinterview.booklibrary.api.books.bookdtos.BookMappers;
import com.appdomeinterview.booklibrary.domain.Book;
import com.appdomeinterview.booklibrary.technical.cache.InMemoryCache;
import com.appdomeinterview.booklibrary.technical.repositories.BookRepository;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    final BookRepository bookRepository;

    final BookMappers bookMappers;

    final InMemoryCache inMemoryCache;

    public BookService(@Autowired BookRepository bookRepository,
                       @Autowired BookMappers bookMappers,
                       @Autowired InMemoryCache inMemoryCache) {
        this.bookRepository = bookRepository;
        this.bookMappers = bookMappers;
        this.inMemoryCache = inMemoryCache;
    }

    @SneakyThrows
    public Book getBookByName(String bookName) {
        return inMemoryCache.cache.get(bookName);
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
