package com.booklibrary.api.books.bookdtos;

public class BookErrors  {

    public static class BookDoesNotExistException extends IllegalArgumentException{
        public BookDoesNotExistException(String errorMessage){
            super(errorMessage);
        }
    }

}
