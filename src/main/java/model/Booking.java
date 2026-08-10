package model;

import enums.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Booking {
    private final String id;
    private LocalDateTime bookingTime;
    private BigDecimal totalAmount;
    private String showId;
    private String userId;
    private BookingStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Booking(Builder builder) {
        this.id = builder.id;
        this.bookingTime = builder.bookingTime;
        this.totalAmount = builder.totalAmount;
        this.showId = builder.showId;
        this.userId = builder.userId;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getShowId() {
        return showId;
    }

    public String getUserId() {
        return userId;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private LocalDateTime bookingTime;
        private BigDecimal totalAmount;
        private String showId;
        private String userId;
        private BookingStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private Builder() {

        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder bookingTime(LocalDateTime bookingTime) {
            this.bookingTime = bookingTime;
            return this;
        }

        public Builder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder showId(String showId) {
            this.showId = showId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder status(BookingStatus status) {
            this.status = status;
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

        public Booking build() {
            if (this.bookingTime == null) {
                throw new IllegalStateException("Booking time cannot be null");
            }

            if (this.totalAmount.equals(BigDecimal.ZERO)) {
                throw new IllegalStateException("Amount must be greater than or equal to 0");
            }

            if (this.showId == null || this.showId.isBlank()) {
                throw new IllegalStateException("Show ID cannot be blank");
            }

            if (this.userId == null || this.userId.isBlank()) {
                throw new IllegalStateException("User ID cannot be blank");
            }

            if (this.status == null) {
                throw new IllegalStateException("Status cannot be null");
            }

            if (this.createdAt == null) {
                this.createdAt = LocalDateTime.now();
            }

            return new Booking(this);
        }
    }

}
