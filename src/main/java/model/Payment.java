package model;

import enums.Currency;
import enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private final String id;
    private Currency currency;
    private String transactionId;
    private final String bookingId;
    private BigDecimal amount;
    private final LocalDateTime paidAt;
    private String gatewayResponse;
    private PaymentStatus status;

    public Payment(Builder builder) {
        this.id = builder.id;
        this.currency = builder.currency;
        this.transactionId=builder.transactionId;
        this.bookingId = builder.bookingId;
        this.amount = builder.amount;
        this.paidAt = builder.paidAt;
        this.gatewayResponse = builder.gatewayResponse;
        this.status = builder.status;
    }

    public String getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getBookingId() {
        return bookingId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public String getGatewayResponse() {
        return gatewayResponse;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setGatewayResponse(String gatewayResponse) {
        this.gatewayResponse = gatewayResponse;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private Currency currency;
        private String transactionId;
        private String bookingId;
        private BigDecimal amount;
        private LocalDateTime paidAt;
        private String gatewayResponse;
        private PaymentStatus status;

        private Builder() {

        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder transactionId(String transactionId){
            this.transactionId=transactionId;
            return this;
        }

        public Builder bookingId(String bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder paidAt(LocalDateTime paidAt) {
            this.paidAt = paidAt;
            return this;
        }

        public Builder gatewayResponse(String gatewayResponse) {
            this.gatewayResponse = gatewayResponse;
            return this;
        }

        public Builder status(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public Payment build() {

            if (currency == null) {
                throw new IllegalStateException("Currency cannot be null");
            }

            if(transactionId == null || this.transactionId.isBlank()){
                throw new IllegalStateException("Transaction ID connot be blank");
            }

            if (this.bookingId == null || this.bookingId.isBlank()) {
                throw new IllegalStateException("Booking ID cannot be blank");
            }

            if (this.amount.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalStateException("Amount cannot be less than 0");
            }

            if (this.paidAt == null) {
                this.paidAt = LocalDateTime.now();
            }

            if (this.status == null) {
                throw new IllegalStateException("Status cannot be null");
            }

            return new Payment(this);
        }
    }

}
