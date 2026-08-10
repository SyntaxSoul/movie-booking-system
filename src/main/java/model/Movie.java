package model;

import enums.Language;
import enums.MovieCertificate;
import enums.MovieGenre;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movie {
    private final String id;
    private String title;
    private MovieGenre genre;
    private Language language;
    private int durationMinutes;
    private String description;
    private LocalDate releaseDate;
    private String posterUrl;
    private String trailerUrl;
    private MovieCertificate certificate;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Movie(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.genre = builder.genre;
        this.language = builder.language;
        this.durationMinutes = builder.durationMinutes;
        this.description = builder.description;
        this.releaseDate = builder.releaseDate;
        this.posterUrl = builder.posterUrl;
        this.trailerUrl = builder.trailerUrl;
        this.certificate = builder.certificate;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public Language getLanguage() {
        return language;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public MovieCertificate getCertificate() {
        return certificate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public void setCertificate(MovieCertificate certificate) {
        this.certificate = certificate;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String title;
        private MovieGenre genre;
        private Language language;
        private int durationMinutes;
        private String description;
        private LocalDate releaseDate;
        private String posterUrl;
        private String trailerUrl;
        private MovieCertificate certificate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private Builder() {

        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder genre(MovieGenre genre) {
            this.genre = genre;
            return this;
        }

        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        public Builder durationMinutes(int durationMinutes) {
            this.durationMinutes = durationMinutes;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder releaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder trailerUrl(String trailerUrl) {
            this.trailerUrl = trailerUrl;
            return this;
        }

        public Builder posterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }

        public Builder certificate(MovieCertificate certificate) {
            this.certificate = certificate;
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

        public Movie build() {
            if (this.title == null || this.title.isBlank()) {
                throw new IllegalStateException("Title cannot be empty");
            }

            if (this.genre == null) {
                throw new IllegalStateException("Genre cannot be null");
            }

            if (this.language == null) {
                throw new IllegalStateException("Language cannot be null");
            }

            if (this.durationMinutes <= 0) {
                throw new IllegalStateException("Duration must be greater than 0");
            }

            if (this.releaseDate == null) {
                throw new IllegalStateException("Release date cannot be null");
            }

            if (this.certificate == null) {
                throw new IllegalStateException("Certificate cannot be blank");
            }

            if (this.createdAt == null) {
                this.createdAt = LocalDateTime.now();
            }

            return new Movie(this);
        }
    }
}
