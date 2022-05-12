package com.appdomeinterview.booklibrary.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column(name = "book_name", nullable = false)
    String bookName;

    String bookAuthor;

    Integer numberOfPages;

}
