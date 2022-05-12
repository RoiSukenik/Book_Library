create TABLE books
(
    book_name       VARCHAR(255) NOT NULL,
    book_author     VARCHAR(255),
    number_of_pages INT,
    timestamp       TIMESTAMP,
    CONSTRAINT pk_books PRIMARY KEY (book_name)
);