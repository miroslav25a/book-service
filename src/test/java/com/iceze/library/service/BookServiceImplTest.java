package com.iceze.library.service;

import com.iceze.library.model.dto.BookDTO;
import com.iceze.library.BookRepositoryStub;
import com.iceze.library.dao.BookRepository;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BookServiceImplTest {
    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";
    private static final String BOOK_REFERENCE_PREFIX_INVALID = "REFERENCE-";
    private static final String THE_GRUFFALO_REFERENCE = BOOK_REFERENCE_PREFIX + "GRUFF472";
    private static final String THE_WILL_REFERENCE = BOOK_REFERENCE_PREFIX +"WILL987";
    private static final String THE_GRUFFALO_REFERENCE_UNKNOWN = BOOK_REFERENCE_PREFIX + "999";
    private static final String THE_GRUFFALO_REFERENCE_INVALID = BOOK_REFERENCE_PREFIX_INVALID + "GRUFF472";
    private static final String THE_GRUFFALO_TITLE = "The Gruffalo";
    private static final String THE_GRUFFALO_REVIEW = "A mouse taking a walk in the woods";
    private static final String THE_WIND_IN_THE_WILLOWS_NO_REVIEW_REFERENCE = BOOK_REFERENCE_PREFIX + "WILL988";

    private BookService bookService;
    private BookRepository bookRepository;

    @Before
    public void setup() {
        this.bookRepository = new BookRepositoryStub();
        this.bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void shouldRetrieveBook() {
        final BookDTO result = bookService.retrieveBook(THE_GRUFFALO_REFERENCE);

        assertThat(result).isNotNull();
        assertThat(result.getReference()).isEqualTo(THE_GRUFFALO_REFERENCE);
        assertThat(result.getTitle()).isEqualTo(THE_GRUFFALO_TITLE);
        assertThat(result.getReview()).isEqualTo(THE_GRUFFALO_REVIEW);
    }

    @Test
    public void shouldValidateBookReference() {
        assertThatExceptionOfType(BookNotFoundException.class)
                .isThrownBy(() -> bookService.retrieveBook(THE_GRUFFALO_REFERENCE_INVALID))
                .withMessage(String.format("invalid book reference value: %s", THE_GRUFFALO_REFERENCE_INVALID))
                .withStackTraceContaining("BookNotFoundException")
                .withNoCause();
    }

    @Test
    public void shouldThrowExceptionIfBookReferenceDoesNotExist() {
        assertThatExceptionOfType(BookNotFoundException.class)
                .isThrownBy(() -> bookService.retrieveBook(THE_GRUFFALO_REFERENCE_UNKNOWN))
                .withMessage(String.format("book with reference value: %s does not exist", THE_GRUFFALO_REFERENCE_UNKNOWN))
                .withStackTraceContaining("BookNotFoundException")
                .withNoCause();
    }

    @Test
    public void shouldGetBookSummaryWithReviewOf9WordsOrLess() {
        final String result = bookService.getBookSummary(THE_GRUFFALO_REFERENCE);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("[BOOK-GRUFF472] The Gruffalo - A mouse taking a walk in the woods");
    }

    @Test
    public void shouldGetBookSummaryWithReviewOfMoreThan9Words() {
        final String result = bookService.getBookSummary(THE_WILL_REFERENCE);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("[BOOK-WILL987] The Wind In The Willows - " +
                "With the arrival of spring and fine weather outside...");
    }

    @Test
    public void shouldGetBookSummaryWithBlankReview() {
        final String result = bookService.getBookSummary(THE_WIND_IN_THE_WILLOWS_NO_REVIEW_REFERENCE);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("[BOOK-WILL988] The Wind In The Willows - ");
    }

    @Test
    public void shouldGetBookSummaryValidateBookReference() {
        assertThatExceptionOfType(BookNotFoundException.class)
                .isThrownBy(() -> bookService.getBookSummary(THE_GRUFFALO_REFERENCE_INVALID))
                .withMessage(String.format("invalid book reference value: %s", THE_GRUFFALO_REFERENCE_INVALID))
                .withStackTraceContaining("BookNotFoundException")
                .withNoCause();
    }

    @Test
    public void shouldGetBookSummaryThrowExceptionIfBookReferenceDoesNotExist() {
        assertThatExceptionOfType(BookNotFoundException.class)
                .isThrownBy(() -> bookService.getBookSummary(THE_GRUFFALO_REFERENCE_UNKNOWN))
                .withMessage(String.format("book with reference value: %s does not exist", THE_GRUFFALO_REFERENCE_UNKNOWN))
                .withStackTraceContaining("BookNotFoundException")
                .withNoCause();
    }
}
