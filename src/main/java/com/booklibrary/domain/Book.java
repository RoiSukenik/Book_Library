package com.booklibrary.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {
    @Id
    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column
    private String bookAuthor;

    @Column
    private Integer numberOfPages;

    @CreationTimestamp
    @Column
    private Timestamp timestamp;

    public Book() {

    }

    public Book(String name, String author, int numberOfPages) {
        this.bookName = name;
        this.bookAuthor = author;
        this.numberOfPages = numberOfPages;
    }
}
