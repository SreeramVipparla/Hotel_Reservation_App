# Hotel Reservation App

# Overview

<img src="https://user-images.githubusercontent.com/86887626/143671843-021c8c4e-a43f-427c-aa9e-ae2bbe25e3ea.jpg" width="1000" height="250">

# Introduction

In this project I have created a Hotel Reservation App using Java. This app allows user to choose options based on their choice. The features of the app are as follows-

# App Features

1. User Scenarios

- **Creating a customer account:** The user needs to first create a customer account before they can create a reservation.
- **Searching for rooms:** The app should allow the user to search for available rooms based on provided checkin and checkout dates. If the application has available rooms for the specified date range, a list of the corresponding rooms will be displayed to the user for choosing.
- **Booking a room:** Once the user has chosen a room, the app will allow them to book the room and create a reservation.
- **Viewing reservations:** After booking a room, the app allows customers to view a list of all their reservations.

2. Admin Scenarios

- **Displaying all customers accounts.**
- **Viewing all the rooms in the hotel.**
- **Viewing all the hotel reservations.**
- **Adding a room to the hotel application.**

3. Reservation Requirements

- **Avoid conflicting reservations:** A single room may only be reserved by a single customer per a checkin and checkout date range.
- **Search for recommended rooms:** If there are no available rooms for the customer's date range, a search will be performed that displays recommended rooms on alternative dates. The recommended room search will add seven days to the original checkin and checkout dates to see if the hotel has any availabilities, and then display the recommended rooms/dates to the customer.

4. Room Requirements

- **Room cost:** Rooms will contain a price per night. When displaying rooms, paid rooms will display the price per night and free rooms will display "Free" or have a $0 price.
- **Unique room numbers:** Each room will have a unique room number, meaning that no two rooms can have the same room number.
- **Room type:** Rooms can be either single occupant or double occupant.

5. Customer Requirements

- **A unique email for the customer:** RegEx is used to check that the email is in the correct format (i.e., name@domain.com).
- **A first name and last name.**

6. **No crashing:** The application does not crash based on user input.

- **No unhandled exceptions:** The app has `try` and `catch` blocks that are used to capture exceptions and provide useful information to the user. There are no unhandled exceptions.
