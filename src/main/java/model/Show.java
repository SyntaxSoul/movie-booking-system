package model;

import enums.ShowStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Show {
    private final String id;
    private String movieId;
    private String screenId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ShowStatus status;
    private BigDecimal price;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getId() {
        return id;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getScreenId() {
        return screenId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ShowStatus getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(ShowStatus status) {
        this.status = status;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Show(Builder builder) {
        this.id = builder.id;
        this.movieId = builder.movieId;
        this.screenId = builder.screenId;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.status = builder.status;
        this.price = builder.price;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String movieId;
        private String screenId;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private ShowStatus status;
        private BigDecimal price;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private Builder() {

        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder movidId(String movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder screenId(String screenId) {
            this.screenId = screenId;
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder status(ShowStatus status) {
            this.status = status;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Show build() {

            if (this.movieId == null || this.movieId.isBlank()) {
                throw new IllegalStateException("Movie ID cannot be blank");
            }

            if (this.screenId == null || this.screenId.isBlank()) {
                throw new IllegalStateException("Screen ID cannot be blank");
            }

            if (this.startTime == null) {
                throw new IllegalStateException("Start time cannot be null");
            }

            if (this.endTime == null) {
                throw new IllegalStateException("End time cannot be null");
            }

            if (this.status == null) {
                throw new IllegalStateException("Show status cannot be null");
            }

            if (this.price == null) {
                throw new IllegalStateException("Price cannot be null");
            }

            if (this.createdAt == null) {
                this.createdAt = LocalDateTime.now();
            }

            return new Show(this);
        }
    }
}
