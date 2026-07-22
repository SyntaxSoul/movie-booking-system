# Business Rules

## User
- Email must be unique
- User must verify email before booking
- Dob is mandatory
- Can book multiple tickets

## Theatre
- Theatre must be verified before creating show
- One theatre has multiple screens

## Screen
- Screen belong to only one theatre
- Seat number must be unique within a screen

## Show
- Show must not overlap for a screen
- Show start time must be before end time
- One screen can have multiple shows
- A movie can have multiple shows
- One show belongs to one screen

## Booking 
- One booking belongs to one show
- One booking can have multiple payment attempts.
- Only one payment can be successful.
- One booking belongs to one user
- A booking can contain multiple seats
- Booking status: PENDING, CONFIRMED, CANCELLED

## Payment
- Every payment belongs to one booking.
- A booking can have multiple payment attempts.
- Ticket is generated only after a successful payment.

## Seat
- A seat cannot be booked twice for the same show

## Feedback
- Rating must be between 1-5
- Only one feedback per ticket

#### _Note: Movie, BookedSeat, Ticket don't have special business logic, they just exist for storing data/mapping data._