# Book_Library

This Project is a simple MC project made to act as a simple library backend.

There are 3 API points:
|Path|Params|Method|
|----|----|---|
|/books/{bookName}|String bookName|GET|
|/books/|BookDto {String bookName, String authorName, Integer numberOfPages}|POST|
|/books/{bookName}|String bookName|Delete|

The overall tech stack of this project:
- Java 11
- Google Guava Cahce
- Spring-boot 2.6.7
- H2
- JUnit

