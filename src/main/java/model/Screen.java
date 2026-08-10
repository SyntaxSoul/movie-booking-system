package model;

import enums.ScreenType;

import java.time.LocalDateTime;

public class Screen {
    private final String id;
    private String screenName;
    private String screenNumber;
    private ScreenType screenType;
    private final int capacity;
    private String theatreId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Screen(Builder builder) {
        this.id = builder.id;
        this.screenName = builder.screenName;
        this.screenNumber = builder.screenNumber;
        this.screenType = builder.screenType;
        this.capacity = builder.capacity;
        this.theatreId = builder.theatreId;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getScreenNumber() {
        return screenNumber;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setScreenNumber(String screenNumber) {
        this.screenNumber = screenNumber;
    }

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String screenName;
        private String screenNumber;
        private ScreenType screenType;
        private int capacity;
        private String theatreId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private Builder() {

        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder screenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public Builder screenNumber(String screenNumber) {
            this.screenNumber = screenNumber;
            return this;
        }

        public Builder screenType(ScreenType screenType) {
            this.screenType = screenType;
            return this;
        }

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder theatreId(String theatreId) {
            this.theatreId = theatreId;
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

        public Screen build() {

            if (this.screenNumber == null || this.screenNumber.isBlank()) {
                throw new IllegalStateException("Screen number cannot be blank");
            }

            if (this.screenType == null) {
                throw new IllegalStateException("Screen type cannot be blank");
            }

            if (this.capacity <= 0) {
                throw new IllegalStateException("Capacity must be greater than 0");
            }

            if (this.theatreId == null || this.theatreId.isBlank()) {
                throw new IllegalStateException("Theatre ID cannot be null");
            }

            if (this.createdAt == null) {
                this.createdAt = LocalDateTime.now();
            }

            return new Screen(this);
        }
    }
}