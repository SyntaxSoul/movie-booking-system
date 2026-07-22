# User

purpose:
    Stores user account information.

| Column     | Type         | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT           |
|------------|--------------|----------|--------|-------------|----------------|-------------|-------------------|
| id         | BIGINT       |          |        | YES         | YES            |
| name       | VARCHAR(100) | YES      |
| user_type  | Varchar(25)  | YES      |
| email      | VARCHAR(100) | YES      | YES    |
| phone      | VARCHAR(25)  | YES      | YES    |
| gender     | VARCHAR(15)  |
| dob        | DATE         | YES      |
| status     | VARCHAR(25)  | YES      |        |             |                |             | ACTIVE            |
| password   | VARCHAR(255) | YES      |
| created_at | TIMESTAMP    | YES      |        |             |                |             | CURRENT_TIMESTAMP |
| updated_at | TIMESTAMP    |
| last_login | TIMESTAMP    |


### Business rules:
- Email must be unique
- User must verify before booking
- Dob is mandatory
- Can book multiple tickets


# Movie

purpose:
    Stores movie information

| Column           | Type         | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT |
|------------------|--------------|----------|--------|-------------|----------------|-------------|---------|
| id               | BIGINT       |          |        | YES         | YES            |
| title            | VARCHAR(100) | YES      |
| genre            | VARCHAR(50)  | YES      |
| language         | VARCHAR(50)  | YES      |
| duration_minutes | INT          | YES      |
| description      | TEXT         |
| release_date     | DATE         | YES      |
| poster_url       | VARCHAR(255) |
| trailer_url      | VARCHAR(255) |
| certificate      | VARCHAR(15)  | YES      |
| created_at       | TIMESTAMP    | YES      |
| updated_at       | TIMESTAMP    |


# Theatre

purpose:
    Stores theatre information

| Column          | Type         | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT |
|-----------------|--------------|----------|--------|-------------|----------------|-------------|---------|
| id              | BIGINT       |          |        | YES         | YES            |
| name            | VARCHAR(100) | YES      |
| address         | VARCHAR(255) | YES      |
| phone           | VARCHAR(25)  | YES      | YES    |
| email           | VARCHAR(255) | YES      | YES    |
| owner_id        | BIGINT       | YES      |        |             |                | YES         |
| verified_status | VARCHAR(25)  | YES      |
| license_number  | VARCHAR(255) | YES      | YES    |
| created_at      | TIMESTAMP    | YES      |
| updated_at      | TIMESTAMP    |
| status          | VARCHAR(25)  | YES      |

### Business rules:
- Theatre must be verified before creating show
- One theatre has multiple screens

# Screen

purpose:
    Stores theatre screen information

| Column        | Type        | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT |
|---------------|-------------|----------|--------|-------------|----------------|-------------|---------|
| id            | BIGINT      |          |        | YES         | YES            |
| screen_number | VARCHAR(10) | YES      |
| theatre_id    | BIGINT      | YES      |        |             |                | YES         |
| capacity      | INT         | YES      |
| screen_name   | VARCHAR(20) |
| screen_type   | VARCHAR(25) | YES      |
| created_at    | TIMESTAMP   | YES      |
| updated_at    | TIMESTAMP   |

### Business rules:
- Screen belong to only one theatre
- Seat number must be unique within a screen

# Seat

purpose:
    Stores seats information of theatre

| Column      | Type        | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT |
|-------------|-------------|----------|--------|-------------|----------------|-------------|---------|
| id          | BIGINT      |          |        | YES         | YES            |
| seat_number | VARCHAR(10) | YES      |
| seat_type   | VARCHAR(20) |
| screen_id   | BIGINT      | YES      |        |             |                | YES         |
| created_at  | TIMESTAMP   | YES      |
| updated_at  | TIMESTAMP   |

### Business rules:
- A seat cannot be booked twice for the same show

# Show

purpose:
    Stores movie show information

| Column     | Type          | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT |
|------------|---------------|----------|--------|-------------|----------------|-------------|---------|
| id         | BIGINT        |          |        | YES         | YES            |
| movie_id   | BIGINT        | YES      |        |             |                | YES         |
| screen_id  | BIGINT        | YES      |        |             |                | YES         |
| start_time | TIMESTAMP     | YES      |
| end_time   | TIMESTAMP     | YES      |
| status     | VARCHAR(25)   | YES      |
| price      | DECIMAL(10,2) | YES      |
| created_at | TIMESTAMP     | YES      |
| updated_at | TIMESTAMP     |

### Business rule:
- Show must not overlap for a screen
- Show start time must be before end time
- One screen can have multiple shows
- A movie can have multiple shows
- One show belong to one screen

# Booking

purpose:
    Stores movie booking information

| Column       | Type          | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT |
|--------------|---------------|----------|--------|-------------|----------------|-------------|---------|
| id           | BIGINT        |          |        | YES         | YES            |
| booking_time | TIMESTAMP     | YES      |
| total_amount | DECIMAL(10,2) | YES      |
| show_id      | BIGINT        | YES      |        |             |                | YES         |
| user_id      | BIGINT        | YES      |        |             |                | YES         |
| status       | VARCHAR(25)   | YES      |
| created_at   | TIMESTAMP     | YES      |
| updated_at   | TIMESTAMP     |

### Business rule:
- One booking belongs to one show
- One booking can have multiple payment attempts
- Only one payment can be successful
- One booking belongs to one user
- One booking can contain multiple seats.
- Booking status: PENDING, CONFIRMED, CANCELLED

# BookedSeat

Purpose:
    Stores the booked seats information

| Column     | Type          | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT |
|------------|---------------|----------|--------|-------------|----------------|-------------|---------|
| id         | BIGINT        |          |        | YES         | YES            |
| price      | DECIMAL(10,2) | YES      |
| seat_id    | BIGINT        | YES      |        |             |                | YES         |
| booking_id | BIGINT        | YES      |        |             |                | YES         |

# Ticket

purpose:
    Stores movie ticket information

| Column     | Type         | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT |
|------------|--------------|----------|--------|-------------|----------------|-------------|---------|
| id         | BIGINT       |          |        | YES         | YES            |
| booking_id | BIGINT       | YES      | YES    |             |                | YES         |
| issued_at  | TIMESTAMP    | YES      |
| status     | VARCHAR(25)  | YES      |
| qr_code    | VARCHAR(255) | YES      |

# Feedback

Purpose:
    Stores the user feedback on theatre and booked movie

| Column         | Type      | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT |
|----------------|-----------|----------|--------|-------------|----------------|-------------|---------|
| id             | BIGINT    |          |        | YES         | YES            |
| ticket_id      | BIGINT    | YES      |        |             |                | YES         |
| theatre_rating | INT       | YES      |
| movie_rating   | INT       | YES      |
| comment        | TEXT      |
| created_at     | TIMESTAMP | YES      |

### Business rules:
- Ratings must be between 1-5

# Payment

Purpose:
    Stores booking payment information

| Column           | Type          | Not Null | Unique | Primary Key | Auto Increment | Foreign Key | DEFAULT |
|------------------|---------------|----------|--------|-------------|----------------|-------------|---------|
| id               | BIGINT        |          |        | YES         | YES            |
| currency         | VARCHAR(3)    | YES      |
| transaction_id   | VARCHAR(255)  | YES      | YES    |
| booking_id       | BIGINT        | YES      |        |             |                | YES         |
| amount           | DECIMAL(10,2) | YES      |
| paid_at          | TIMESTAMP     | YES      |
| gateway_response | VARCHAR(255)  |
| status           | VARCHAR(25)   | YES      |

### Business rules:
- One payment belongs to one booking
- Ticket is generated only after successful payment
- One booking can have one successful payment and multiple failed payments
