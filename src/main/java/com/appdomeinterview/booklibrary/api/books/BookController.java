package com.appdomeinterview.booklibrary.api.books;

import com.appdomeinterview.booklibrary.api.books.bookdtos.BookDto;
import com.appdomeinterview.booklibrary.api.books.bookdtos.BookMappers;
import com.appdomeinterview.booklibrary.application.BookService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController(BooksApi.BASE_PATH)
public class BookController implements BooksApi {

    @Autowired
    BookService bookService;

    @Autowired
    BookMappers bookMappers;

    @Override
    public BookDto getBookByName(String bookName) {
        val domainBook = bookService.getBookByName(bookName);
        return bookMappers.map(domainBook);
    }

    @Override
    public String addBook(BookDto newBook) {
        val domainBook = bookService.addBook(newBook);
        return domainBook.getBookName();
    }

    @Override
    public void deleteBook(String bookName) {
        bookService.deleteBookByName(bookName);
    }
}
