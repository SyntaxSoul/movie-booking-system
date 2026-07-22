# Entity Discovery

### Entities
- User
- Theatre
- Movie
- Show
- Screen
- Seat
- Payment
- Booking
- Ticket
- Feedback
- BookedSeat

### User
- id
- email
- phone
- name
- user_type
- gender
- dob
- password
- status
- createdAt
- updatedAt
- lastLogin

### Movie
- id
- title
- duration_minutes
- language
- genre
- certificate
- description
- release_date
- poster_url
- trailer_url
- created_at
- updated_at

### Theatre
- id
- name
- license_number
- verified_status
- email
- phone
- address
- status
- owner_id
- created_at
- updated_at

### Screen
- id
- screen_name
- screen_number
- screen_type
- capacity
- theatre_id
- created_at
- updated_at

### Seat
- id
- seat_number
- seat_type
- screen_id
- created_at
- updated_at

### Show
- id
- screen_id
- movie_id
- start_time
- end-time
- status
- price
- created_at
- updated_at

### Booking
- id
- booking_time
- total_amount
- show_id
- user_id
- status
- created_at
- updated_at

### BookedSeat
- id
- booking_id
- seat_id
- price

### Ticket
- id
- booking_id
- issued_at
- status
- qr_code

### Feedback
- id
- ticket_id
- movie_rating
- theatre_rating
- comment
- created_at

### Payment
- id
- booking_id
- transaction_id
- currency
- amount
- paid_at
- gateway_response
- status