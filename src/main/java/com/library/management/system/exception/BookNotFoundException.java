package com.library.management.system.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String message){
        super(message);
    }
}
