package com.appdomeinterview.booklibrary.application;

import com.appdomeinterview.booklibrary.api.books.bookdtos.BookDto;
import com.appdomeinterview.booklibrary.api.books.bookdtos.BookMappers;
import com.appdomeinterview.booklibrary.domain.Book;
import com.appdomeinterview.booklibrary.technical.repositories.BookRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    final
    BookRepository bookRepository;

    final
    BookMappers bookMappers;

    public BookService(@Autowired BookRepository bookRepository, @Autowired BookMappers bookMappers) {
        this.bookRepository = bookRepository;
        this.bookMappers = bookMappers;
    }

    public Book getBookByName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }


    public Book addBook(BookDto bookDto) {
        val domainBook = bookMappers.map(bookDto);

        bookRepository.save(domainBook);
        return domainBook;
    }

    public void deleteBookByName(String bookName){
        bookRepository.deleteBookByBookName(bookName);
    }

}
