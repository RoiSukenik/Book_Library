package com.booklibrary.api.books.bookdtos;

import com.booklibrary.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMappers {

    public Book map(BookDto bookDto) {
        return new Book(bookDto.getBookName(), bookDto.getBookAuthor(), bookDto.getNumberOfPages());
    }

    public BookDto map(Book book) {
        return BookDto.builder()
                      .bookName(book.getBookName())
                      .bookAuthor(book.getBookAuthor())
                      .numberOfPages(book.getNumberOfPages())
                      .build();
    }
}
