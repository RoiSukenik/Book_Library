package com.booklibrary.application;

import com.booklibrary.api.books.bookdtos.BookDto;
import com.booklibrary.api.books.bookdtos.BookErrors;
import com.booklibrary.api.books.bookdtos.BookMappers;
import com.booklibrary.domain.Book;
import com.booklibrary.technical.cache.InMemoryCache;
import com.booklibrary.technical.repositories.BookRepository;
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
        bookRepository.findByBookName(bookName).map(it -> {
                          bookRepository.deleteBookByBookName(it.getBookName());
                          return it;
                      })
                      .orElseThrow(() -> new BookErrors.BookDoesNotExistException("Can't delete a book which does not exist!"));
    }

}
