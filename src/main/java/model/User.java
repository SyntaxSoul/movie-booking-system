package model;

import enums.Gender;
import enums.UserStatus;
import enums.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private final String id;
    private String name;
    private UserType userType;
    private String email;
    private String phone;
    private Gender gender;
    private LocalDate dob;
    private UserStatus status;
    private String password;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.userType = builder.userType;
        this.email = builder.email;
        this.phone = builder.phone;
        this.gender = builder.gender;
        this.dob = builder.dob;
        this.password = builder.password;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.lastLogin = builder.lastLogin;
    }

    // Getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    //Builder Code

    // Static factory to get a builder object
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private UserType userType;
        private String email;
        private String phone;
        private Gender gender;
        private LocalDate dob;
        private UserStatus status;
        private String password;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime lastLogin;


        // Empty builder constructor to build using chaining
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

        public Builder userType(UserType userType) {
            this.userType = userType;
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

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder dob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public Builder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
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

        public Builder lastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public User build() {
            if (this.name == null || this.name.isBlank()) {
                throw new IllegalStateException("Name must not be empty");
            }

            if (this.userType == null) {
                throw new IllegalStateException("User type must not be null");
            }

            if (this.email == null || this.email.isBlank()) {
                throw new IllegalStateException("Email id must not be blank");
            }

            if (this.gender == null) {
                throw new IllegalStateException("Gender must not be null");
            }

            if (this.dob == null) {
                throw new IllegalStateException("DOB must not be null");
            }

            if (this.status == null) {
                this.status = UserStatus.ACTIVE;
            }

            if (this.createdAt == null) {
                this.createdAt = LocalDateTime.now();
            }

            if (this.password == null || this.password.isBlank()) {
                throw new IllegalStateException("Empty password field!");
            }
            return new User(this);
        }

    }
}
