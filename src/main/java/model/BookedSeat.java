package model;

import java.math.BigDecimal;

public class BookedSeat {
    private final String id;
    private BigDecimal price;
    private String seatId;
    private String bookingId;

    public BookedSeat(Builder builder) {
        this.id = builder.id;
        this.price = builder.price;
        this.seatId = builder.seatId;
        this.bookingId = builder.bookingId;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private BigDecimal price;
        private String seatId;
        private String bookingId;

        private Builder() {

        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder seatId(String seatId) {
            this.seatId = seatId;
            return this;
        }

        public Builder bookingId(String bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public BookedSeat build() {

            if (this.price.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalStateException("Price cannot be less than 0");
            }

            if (this.seatId == null || this.seatId.isBlank()) {
                throw new IllegalStateException("Seat ID cannot be blank");
            }

            if (this.bookingId == null || this.bookingId.isBlank()) {
                throw new IllegalStateException("Booking ID cannot be blank");
            }

            return new BookedSeat(this);
        }
    }
}
