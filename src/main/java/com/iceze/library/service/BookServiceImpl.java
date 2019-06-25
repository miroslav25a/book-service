package com.iceze.library.service;

import com.google.common.base.Joiner;
import com.iceze.library.model.dto.BookDTO;
import com.iceze.library.dao.BookRepository;
import com.iceze.library.model.entity.BookEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";
    private static final String BOOK_SUMMARY_REGEX = "\\W+";
    private static final int BOOK_SUMMARY_MAX_WORDS = 9;

    private BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO retrieveBook(final String bookReference) throws BookNotFoundException {
        if(!validateBookReference(bookReference)) {
            throw new BookNotFoundException(String.format("invalid book reference value: %s", bookReference));
        }

        Optional<BookEntity> optionalBookEntity = bookRepository.retrieveBook(bookReference);

        optionalBookEntity.orElseThrow(() -> new BookNotFoundException(String.format(
                "book with reference value: %s does not exist", bookReference)));

        BookEntity bookEntity = optionalBookEntity.get();

        return BookDTO.builder().reference(bookEntity.getReference())
                .title(bookEntity.getTitle())
                .review(bookEntity.getReview())
                .build();
    }

    @Override
    public String getBookSummary(final String bookReference) throws BookNotFoundException {
        BookDTO book = retrieveBook(bookReference);

        return buildBookSummary(book);
    }

    private String buildBookSummary(final BookDTO book) {
        StringBuffer sb = new StringBuffer("[");
        sb.append(book.getReference());
        sb.append("] ");
        sb.append(book.getTitle());
        sb.append(" - ");

        if(StringUtils.isNotBlank(book.getReview())) {
            String[] reviewWords = book.getReview().split(BOOK_SUMMARY_REGEX);

            List<String> wordsList = Arrays.asList(reviewWords).stream()
                    .filter(w -> StringUtils.isNotBlank(w))
                    .collect(Collectors.toList());

            sb.append((wordsList.size() > BOOK_SUMMARY_MAX_WORDS) ?
                    (Joiner.on(" ").join(wordsList.subList(0, BOOK_SUMMARY_MAX_WORDS))) + "..." :
                    book.getReview());
        }

        return sb.toString();
    }

    private boolean validateBookReference(final String bookReference) {
        return (StringUtils.isNotBlank(bookReference) &&
                bookReference.trim().startsWith(BOOK_REFERENCE_PREFIX)) ? true : false;
    }
}
