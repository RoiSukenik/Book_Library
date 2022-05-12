package com.booklibrary.api.books.bookdtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class BookDto {
    private String bookName;
    private String bookAuthor;
    private Integer numberOfPages;
}

