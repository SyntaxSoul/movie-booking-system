package model;

import enums.TheatreStatus;
import enums.TheatreVerifiedStatus;

import java.time.LocalDateTime;

public class Theatre {
    private final String id;
    private String name;
    private String licenseNumber;
    private TheatreVerifiedStatus verifiedStatus;
    private String email;
    private String phone;
    private String address;
    private TheatreStatus status;
    private String ownerId;
    private final LocalDateTime createdAt;
    private LocalDateTime updateAt;

    private Theatre(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.licenseNumber = builder.licenseNumber;
        this.verifiedStatus = builder.verifiedStatus;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.status = builder.status;
        this.ownerId = builder.ownerId;
        this.createdAt = builder.createdAt;
        this.updateAt = builder.updateAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public TheatreVerifiedStatus getVerifiedStatus() {
        return verifiedStatus;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public TheatreStatus getStatus() {
        return status;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setVerifiedStatus(TheatreVerifiedStatus verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(TheatreStatus status) {
        this.status = status;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String licenseNumber;
        private TheatreVerifiedStatus verifiedStatus;
        private String email;
        private String phone;
        private String address;
        private TheatreStatus status;
        private String ownerId;
        private LocalDateTime createdAt;
        private LocalDateTime updateAt;

        private Builder() {

        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder licenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return this;
        }

        public Builder verifiedStatus(TheatreVerifiedStatus verifiedStatus) {
            this.verifiedStatus = verifiedStatus;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder status(TheatreStatus status) {
            this.status = status;
            return this;
        }

        public Builder ownerId(String ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public Theatre build() {
            if (this.name == null || this.name.isBlank()) {
                throw new IllegalStateException("Theatre name cannot be blank");
            }

            if (this.licenseNumber == null) {
                throw new IllegalStateException("License number cannot be null");
            }

            if (this.verifiedStatus == null) {
                throw new IllegalStateException("Verification status cannot be null");
            }

            if (this.email == null || this.email.isBlank()) {
                throw new IllegalStateException("Email cannot be blank");
            }

            if (this.phone == null || this.phone.isBlank()) {
                throw new IllegalStateException("Phone number cannot be blank");
            }

            if (this.address == null || this.address.isBlank()) {
                throw new IllegalStateException("Address cannot be blank");
            }

            if (this.status == null) {
                throw new IllegalStateException("Status cannot be null");
            }

            if (this.ownerId == null || this.ownerId.isBlank()) {
                throw new IllegalStateException("Owner ID cannot be blank");
            }

            if (this.createdAt == null) {
                this.createdAt = LocalDateTime.now();
            }

            return new Theatre(this);
        }
    }

}
