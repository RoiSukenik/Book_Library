package com.appdomeinterview.booklibrary.technical.repositories;

import com.appdomeinterview.booklibrary.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
