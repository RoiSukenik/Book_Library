package com.booklibrary.api.books;

import com.booklibrary.api.books.bookdtos.BookDto;
import org.springframework.web.bind.annotation.*;

public interface BooksApi {

    String BASE_PATH = "/books";

    @GetMapping(value = "/{bookName}", name = "Get a book ")
    BookDto getBookByName(@PathVariable("bookName") String bookName);

    @PostMapping(value = "/", name = "Add a book", headers = {"Content-Type=application/json"})
    String addBook(@RequestBody BookDto newBook);

    @DeleteMapping(value = "/{bookName}", name = "Delete a book")
    void deleteBook(@PathVariable("bookName") String bookName);
}
