package com.appdomeinterview.booklibrary.api.books.bookdtos;

import com.appdomeinterview.booklibrary.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMappers {

    public Book map(BookDto bookDto) {
        return Book.builder()
                .bookName(bookDto.getBookName())
                .bookAuthor(bookDto.getBookAuthor())
                .numberOfPages(bookDto.getNumberOfPages())
                .build();
    }

    public BookDto map(Book book){
        return BookDto.builder()
                .bookName(book.getBookName())
                .bookAuthor(book.getBookAuthor())
                .numberOfPages(book.getNumberOfPages())
                .build();
    }
}
