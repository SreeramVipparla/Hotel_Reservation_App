import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

/**
 * @author-VipparlaSreeram
 */

    public static boolean executeOption(Scanner scanner, Integer selection) {
        boolean keepRunning = true;
        switch (selection) {
            case 1:
                createAccount(scanner);
            break;
            case 2:
                findAndReserveRoom(scanner);
                break;
            case 3:
                getCustomerReservations(scanner);
                break;
            case 4:
                runAdminMenu(scanner);
                break;
            case 5:
                keepRunning = false;
                break;
            default:
                System.out.println("Please enter a number from the above options in the menu :-");
        }
        return keepRunning;
    }
    public static void Menu_Options() {
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println();
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("1. Create an Account");
        System.out.println("2. Find and reserve a room");
        System.out.println("3. See my reservations");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("Please enter a number from the above options in the menu :-");
    }
    private static void runAdminMenu(Scanner scanner) {
        boolean Stay_in_AdminRunning = true;
        while (Stay_in_AdminRunning) {
            try {
                AdminMenu.Menu_Options();
                int adminSelection = Integer.parseInt(scanner.nextLine());
                Stay_in_AdminRunning = AdminMenu.executeOption(scanner, adminSelection);
            } catch (Exception ex) {
                System.out.println("Please enter a number between 1 and 5:-");
            }
        }
    }
    private static void getCustomerReservations(Scanner scanner) {
        System.out.println("Please enter your Email (format: name@example.com): ");
        String email = scanner.nextLine();
        Customer customer = HotelResource.getCustomer(email);
        if (customer == null) {
            System.out.println("Sorry, no account exists for that email");
            return;
        }
        Collection<Reservation> reservations = HotelResource.getCustomerReservations(customer.getEmail());
        if (reservations.isEmpty()) {
            System.out.println("No reservations at the moment");
            return;
        }
        for (Reservation reservation : reservations) {
            System.out.println(reservation.toString());
        }
    }

    private static String createAccount(Scanner scanner) {
        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();
        String email = null;
        boolean validEmail = false;
        while (!validEmail) {
            try {
                System.out.println("Email (format: name@example.com): ");
                email = scanner.nextLine();
                HotelResource.createCustomer(email, firstName, lastName);
                System.out.println("Account created successfully!\n");
                validEmail = true;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
        return email;
    }




    private static Date getValidCheckInDate(Scanner scanner) {
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        Date checkInDate = null;
        boolean validCheckInDate = false;
        while (!validCheckInDate) {
            System.out.println("Check-in date (mm/dd/yyyy): ");
            String inputCheckInDate = scanner.nextLine();
            try {
                checkInDate = DateFor.parse(inputCheckInDate);
                Date today = new Date();
                if (checkInDate.before(today)) { 
                    System.out.println("The check-in date cannot be in the past");
                } else {
                    validCheckInDate = true;
                }
            } catch (ParseException ex) {
                System.out.println("Invalid date format, please use dd/mm/yyyy");
            }
        }
        return checkInDate;
    }
    private static Date getValidCheckOutDate(Scanner scanner, Date checkInDate) {
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        Date checkOutDate = null;
        boolean validCheckOutDate = false;
        while (!validCheckOutDate) {
            System.out.println("Check-out date (mm/dd/yyyy): ");
            String inputCheckOutDate = scanner.nextLine();
            try {
                checkOutDate = DateFor.parse(inputCheckOutDate);
                if (checkOutDate.before(checkInDate)) { 
                    System.out.println("Please reenter the correct date ");
                } else {
                    validCheckOutDate = true;
                }
            } catch (ParseException ex) {
                System.out.println("Invalid date format, please use dd/mm/yyyy");
            }
        }
        return checkOutDate;
    }
    private static void findAndReserveRoom(Scanner scanner) {
        Date checkInDate = getValidCheckInDate(scanner);
        Date checkOutDate = getValidCheckOutDate(scanner, checkInDate);
        Collection<IRoom> availableRooms = HotelResource.findRooms(checkInDate, checkOutDate);
        boolean wantsToBook = false;
        if (availableRooms.isEmpty()) {
            Date newCheckInDate = getRecommendedDate(checkInDate);
            Date newCheckOutDate = getRecommendedDate(checkOutDate);
            availableRooms = HotelResource.findRooms(newCheckInDate, newCheckOutDate);
            if (availableRooms.isEmpty()) {
                System.out.println("There are no available rooms for those dates");
                
            }
        } else {
            System.out.println("Available rooms for check-in on " + checkInDate + " and check-out on " + checkOutDate);
            wantsToBook = showAvailableRoomsAndAskToBook(scanner, availableRooms);
        }
        if (!wantsToBook) {
            return;
        }
        Customer customer = getCustomerForReservation(scanner);
        if (customer == null) {
            System.out.println(" account does not exist for that email");
            return;
        }

        IRoom room = getRoomForReservation(scanner, availableRooms);

        Reservation reservation = HotelResource.bookRoom(customer.getEmail(), room, checkInDate, checkOutDate);
        if (reservation == null) {
            System.out.println("Couldn't process your booking, the room is not available"); 
        } else {
            System.out.println("Your room has been booked successfully!");
            System.out.println(reservation);
        }
    }


    private static Date getRecommendedDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        return c.getTime();
    }

    private static boolean showAvailableRoomsAndAskToBook(Scanner scanner, Collection<IRoom> availableRooms) {
        for (IRoom room : availableRooms) {
            System.out.println(room.toString());
        }
        System.out.println();
        System.out.println("Would you like to book a room? Enter y/yes, or any other character for no:");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
            return true;
        }
        return false;
    }

    private static Customer getCustomerForReservation(Scanner scanner) {
        String email = null;
        boolean hasAccount = false;
        System.out.println("Do you already have an account with us? Enter y/yes, or any other character for no:");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
            hasAccount = true;
        }
        if (hasAccount) {
            System.out.println("Enter your Email (format: name@example.com): ");
            email = scanner.nextLine();
        } else {
            email = createAccount(scanner);
        }
        return HotelResource.getCustomer(email);
    }

    private static IRoom getRoomForReservation(Scanner scanner, Collection<IRoom> availableRooms) {
        IRoom room = null;
        String roomNumber = null;
        boolean validRoomNumber = false;
        while (!validRoomNumber) {
            System.out.println(" Enter the room number: ");
            roomNumber = scanner.nextLine();
            room = HotelResource.getRoom(roomNumber);
            if (room == null) { 
                System.out.println("The specified room doesn't exists, please enter a valid room number");
            } else { 
                if (!availableRooms.contains(room)) {
                    System.out.println("That room is not available, please enter a valid room number");
                } else {
                    validRoomNumber = true;
                }
            }
        }
        return room;
    }

}
