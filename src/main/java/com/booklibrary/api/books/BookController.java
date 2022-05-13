package com.booklibrary.api.books;

import com.booklibrary.api.books.bookdtos.BookDto;
import com.booklibrary.api.books.bookdtos.BookMappers;
import com.booklibrary.application.BookService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BooksApi.BASE_PATH)
public class BookController implements BooksApi {

    private final BookService bookService;

    private final BookMappers bookMappers;

    public BookController(@Autowired BookService bookService, @Autowired BookMappers bookMappers) {
        this.bookService = bookService;
        this.bookMappers = bookMappers;
    }

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
