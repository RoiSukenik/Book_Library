package com.appdomeinterview.booklibrary.technical.repositories;

import com.appdomeinterview.booklibrary.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    Book findByBookName(String name);

    void deleteBookByBookName(String name);
}
