package com.iceze.library.service;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(final String e) {
        super(e);
    }
}
