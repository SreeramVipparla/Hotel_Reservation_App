import api.AdminResource;
import api.HotelResource;
import model.*;

import java.util.Collection;

import java.util.Scanner;
public class AdminMenu {
/**
 * @author-VipparlaSreeram
 */

    public static boolean executeOption(Scanner scanner, Integer selection) {
        boolean Stay_in_AdminRunning = true;
        switch (selection) {

            case 1:
                getAllRooms();
                break;
            case 2:
                getAllCustomers();
                break;
            case 3:
                addRooms(scanner);
                break;
            case 4:
                getAllReservations();
                break;

            case 5:
                Stay_in_AdminRunning = false;
                break;
            default:
                System.out.println("Please enter a number between 1 and 5:-");
        }
        return Stay_in_AdminRunning;
    }
        public static void Menu_Options() {
        System.out.println("Admin Menu");
        System.out.println();
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("1. See all rooms");
        System.out.println("2. See all customers");
        System.out.println("3. Add a room");
        System.out.println("4. See all reservations");
        System.out.println("5. Back to main menu");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("Please enter a number from the above options in the menu:-");
    }

    private static void getAllRooms() {
        Collection<IRoom> rooms;
        rooms = AdminResource.getAllRooms();

        if (rooms.isEmpty()) {
            System.out.println("There are no rooms to display at the moment");
        } else {
            for (IRoom room : rooms) {
                System.out.println(room);
            }
        }
    }

    private static void getAllCustomers() {
        if (AdminResource.getAllCustomers() != null) {
            System.out.println(AdminResource.getAllCustomers());

        } else {

            System.out.println("There aren't customers to show at the moment");
        }
    }


    private static void addRooms(Scanner scanner) {
        boolean keepAddingRooms;
        do {
            addRoom(scanner);
            System.out.println("Would you like to add another room? Enter 'y' for yes or type 'n' for no: ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                keepAddingRooms = true;
            } else {
                keepAddingRooms = false; 
            }
        } while (keepAddingRooms);
    }

    private static void addRoom(Scanner scanner) {
       
        String roomNumber = null;
        boolean validRoomNumber = false;
        while (!validRoomNumber) {
            System.out.println("Enter room number: ");
            roomNumber = scanner.nextLine();
            IRoom roomExists = HotelResource.getRoom(roomNumber);
            if (roomExists != null) { 
                validRoomNumber = true;
            } else { 
                System.out.println("That room already exists. Enter y/yes to update it, or any other character to enter another room number: ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                    validRoomNumber = true;
                }
            }
        }

        double price = 0.00;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                System.out.println("Enter price per night: ");
                price = Double.parseDouble(scanner.nextLine());
                if (price > 0) {
                    validPrice = true;
                } else {
                    System.out.println("The price must be greater or equal than 0.00");
                    
                }
            } catch (Exception ex) {
                System.out.println("Please enter a valid price");
            }
        }

        RoomType roomType = null;
        boolean validRoomType = false;
        while (!validRoomType) {
            try {
                System.out.println("Enter room type (1 for single bed, 2 for double bed): ");
                roomType = RoomType.valueforNumberOfBeds(Integer.parseInt(scanner.nextLine()));
                if (roomType == null) {
                    System.out.println("Please enter a valid room type");
                } else {
                    validRoomType = true;
                }
            } catch (Exception ex) {
                System.out.println("Please enter a valid room type");
            }
        }
        Room newRoom = new Room(roomNumber, price, roomType);
        AdminResource.addRoom(newRoom);
    }



    private static void getAllReservations() {
        Collection<Reservation> allReservations = AdminResource.getAllReservations();
        if (allReservations.isEmpty()) {
            System.out.println("There are no reservations");
        } else {
            for (Reservation reservation : allReservations) {
                System.out.println(reservation.toString());
            }
        }
        System.out.println();
    }

}
