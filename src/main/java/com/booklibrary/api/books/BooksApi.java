package com.booklibrary.api.books;

import com.booklibrary.api.books.bookdtos.BookDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface BooksApi {

    String BASE_PATH = "books";

    @GetMapping(value = "/{bookName}", name = "Get a book ")
    BookDto getBookByName(@PathVariable("bookName") String bookName);

    @PostMapping(value = "/", name = "Add a book")
    String addBook(BookDto newBook);

    @DeleteMapping(value = "/{bookName}", name = "Delete a book")
    void deleteBook(@PathVariable("bookName") String bookName);
}
