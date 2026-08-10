package model;

import java.time.LocalDateTime;

public class Feedback {
    private final String id;
    private final String ticketId;
    private int theatreRating;
    private int movieRating;
    private String comment;
    private final LocalDateTime createdAt;

    public Feedback(Builder builder) {
        this.id = builder.id;
        this.ticketId = builder.ticketId;
        this.theatreRating = builder.theatreRating;
        this.movieRating = builder.movieRating;
        this.comment = builder.comment;
        this.createdAt = builder.createdAt;
    }

    public String getId() {
        return id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public int getTheatreRating() {
        return theatreRating;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setTheatreRating(int theatreRating) {
        this.theatreRating = theatreRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String ticketId;
        private int theatreRating;
        private int movieRating;
        private String comment;
        private LocalDateTime createdAt;

        private Builder() {

        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder ticketId(String ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public Builder theatreRating(int theatreRating) {
            this.theatreRating = theatreRating;
            return this;
        }

        public Builder movieRating(int movieRating) {
            this.movieRating = movieRating;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Feedback build() {

            if (this.ticketId == null || this.ticketId.isBlank()) {
                throw new IllegalStateException("Ticket ID cannot be blank");
            }

            if (this.theatreRating < 0 || this.theatreRating > 5) {
                throw new IllegalStateException("Theatre rating should be between 1-5");
            }

            if (this.movieRating < 0 || this.movieRating > 5) {
                throw new IllegalStateException("Movie rating should be between 1-5");
            }

            if (this.createdAt == null) {
                this.createdAt = LocalDateTime.now();
            }

            return new Feedback(this);
        }

    }
}
