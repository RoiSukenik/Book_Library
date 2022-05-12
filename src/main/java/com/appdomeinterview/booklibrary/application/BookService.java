package com.appdomeinterview.booklibrary.application;

import com.appdomeinterview.booklibrary.api.books.bookdtos.BookDto;
import com.appdomeinterview.booklibrary.api.books.bookdtos.BookErrors;
import com.appdomeinterview.booklibrary.api.books.bookdtos.BookMappers;
import com.appdomeinterview.booklibrary.domain.Book;
import com.appdomeinterview.booklibrary.technical.repositories.BookRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMappers bookMappers;

    public Book getBookByName(String bookName) {
        return bookRepository.findById(bookName).orElseThrow(() -> new BookErrors.BookDoesNotExistException(bookName));
    }

    public Book addBook(BookDto bookDto) {
        val domainBook = bookMappers.map(bookDto);
        bookRepository.save(domainBook);
        return domainBook;
    }

    public void deleteBookByName(String bookName){
        bookRepository.deleteById(bookName);
    }

}
