package com.iceze.library;

import com.iceze.library.dao.BookRepository;
import com.iceze.library.model.entity.BookEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookRepositoryStub implements BookRepository {
    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";

    private static final String THE_GRUFFALO_REFERENCE = BOOK_REFERENCE_PREFIX + "GRUFF472";
    private static final String WINNIE_THE_POOH_REFERENCE = BOOK_REFERENCE_PREFIX + "POOH222";
    private static final String THE_WIND_IN_THE_WILLOWS_REFERENCE = BOOK_REFERENCE_PREFIX + "WILL987";
    private static final String THE_WIND_IN_THE_WILLOWS_NO_REVIEW_REFERENCE = BOOK_REFERENCE_PREFIX + "WILL988";

    private static final Map<String, BookEntity> books;

    static {
        books = new HashMap<>();
        books.put(THE_GRUFFALO_REFERENCE, BookEntity.builder().reference(THE_GRUFFALO_REFERENCE)
                .title("The Gruffalo")
                .review("A mouse taking a walk in the woods")
                .build());
        books.put(WINNIE_THE_POOH_REFERENCE, BookEntity.builder().reference(WINNIE_THE_POOH_REFERENCE)
                .title("Winnie The Pooh")
                .review("In this first volume we meet all the friends from the Hundred Acre Wood.")
                .build());
        books.put(THE_WIND_IN_THE_WILLOWS_REFERENCE, BookEntity.builder().reference(THE_WIND_IN_THE_WILLOWS_REFERENCE)
                .title("The Wind In The Willows")
                .review("With the arrival of spring and fine weather outside, " +
                        "the good-natured Mole loses patience with spring cleaning. He flees his underground home, emerging to " +
                        "take in the air and ends up at the river, which he has " +
                        "never seen before. Here he meets Rat (a water vole), who at this time of year spends all his days in, " +
                        "on and close by the river. Rat takes Mole for a ride " +
                        "in his rowing boat. They get along well and spend many more days boating, with Rat teaching " +
                        "Mole the ways of the river.")
                .build());
        books.put(THE_WIND_IN_THE_WILLOWS_NO_REVIEW_REFERENCE, BookEntity.builder().reference(THE_WIND_IN_THE_WILLOWS_NO_REVIEW_REFERENCE)
                .title("The Wind In The Willows")
                .review(" ")
                .build());
    }

    @Override
    public Optional<BookEntity> retrieveBook(final String reference) {
        return Optional.ofNullable(books.get(reference));
    }
}
