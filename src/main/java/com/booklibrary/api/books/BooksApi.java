package com.booklibrary.api.books;

import com.booklibrary.api.books.bookdtos.BookDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface BooksApi {

    String BASE_PATH = "books";

    @GetMapping(value = "/", name = "Get a book ")
    BookDto getBookByName(String bookName);

    @PostMapping(value = "/", name = "Add a book")
    String addBook(BookDto newBook);

    @DeleteMapping(value = "/{id}",name = "Delete a book")
    void deleteBook(String bookName);
}
