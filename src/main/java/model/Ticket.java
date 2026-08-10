package model;

import enums.TicketStatus;

import java.time.LocalDateTime;

public class Ticket {
    private final String id;
    private String bookingId;
    private final LocalDateTime issuedAt;
    private TicketStatus status;
    private String qrCode;

    public Ticket(Builder builder) {
        this.id = builder.id;
        this.bookingId = builder.bookingId;
        this.issuedAt = builder.issuedAt;
        this.status = builder.status;
        this.qrCode = builder.qrCode;
    }

    public String getId() {
        return id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String bookingId;
        private LocalDateTime issuedAt;
        private TicketStatus status;
        private String qrCode;

        private Builder() {

        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder bookingId(String bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public Builder issuedAt(LocalDateTime issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        public Builder status(TicketStatus status) {
            this.status = status;
            return this;
        }

        public Builder qrCode(String qrCode) {
            this.qrCode = qrCode;
            return this;
        }

        public Ticket build() {

            if (this.bookingId == null || this.bookingId.isBlank()) {
                throw new IllegalStateException("Booking ID cannot be blank");
            }

            if (this.issuedAt == null) {
                this.issuedAt = LocalDateTime.now();
            }

            if (this.status == null) {
                throw new IllegalStateException("Ticket status cannot be null");
            }

            if (this.qrCode == null || this.qrCode.isBlank()) {
                throw new IllegalStateException("QR code cannot be blank");
            }

            return new Ticket(this);
        }

    }
}
