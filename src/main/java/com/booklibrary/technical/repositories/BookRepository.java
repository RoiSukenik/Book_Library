package com.booklibrary.technical.repositories;

import com.booklibrary.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    Optional<Book> findByBookName(String name);

    void deleteBookByBookName(String name);
}
