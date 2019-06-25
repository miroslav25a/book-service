package com.iceze.library.model.dto;

public final class BookDTO {
    private final String reference;
    private final String title;
    private final String review;

    private BookDTO(final Builder builder) {
        this.reference = builder.reference;
        this.title = builder.title;
        this.review = builder.review;
    }

    public String getReview() {
        return review;
    }

    public String getReference() {
        return reference;
    }

    public String getTitle() {
        return title;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String reference;
        private String title;
        private String review;

        private Builder() {
        }

        public Builder reference(final String reference) {
            this.reference = reference;
            return this;
        }

        public Builder title(final String title) {
            this.title = title;
            return this;
        }

        public Builder review(final String review) {
            this.review = review;
            return this;
        }


        public BookDTO build() {
            return new BookDTO(this);
        }
    }
}
