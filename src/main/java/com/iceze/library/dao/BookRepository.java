package com.iceze.library.dao;

import com.iceze.library.model.entity.BookEntity;

import java.util.Optional;

public interface BookRepository {
    Optional<BookEntity> retrieveBook(String reference);
}
