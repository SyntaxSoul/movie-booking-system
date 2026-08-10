package model;

import enums.SeatType;

import java.time.LocalDateTime;

public class Seat {
    private final String id;
    private String seatNumber;
    private SeatType seatType;
    private String screenId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Seat(Builder builder) {
        this.id = builder.id;
        this.seatNumber = builder.seatNumber;
        this.seatType = builder.seatType;
        this.screenId = builder.screenId;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public String getScreenId() {
        return screenId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String seatNumber;
        private SeatType seatType;
        private String screenId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private Builder() {

        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder seatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        public Builder seatType(SeatType seatType) {
            this.seatType = seatType;
            return this;
        }

        public Builder screenId(String screenId) {
            this.screenId = screenId;
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

        public Seat build() {

            if (this.seatNumber == null || this.seatNumber.isBlank()) {
                throw new IllegalStateException("Seat number cannot be empty");
            }

            if (this.seatType == null) {
                throw new IllegalStateException("Seat type cannot be null");
            }

            if (this.screenId == null || this.screenId.isBlank()) {
                throw new IllegalStateException("Screen ID cannot be blank");
            }

            if (this.createdAt == null) {
                this.createdAt = LocalDateTime.now();
            }

            return new Seat(this);
        }
    }
}