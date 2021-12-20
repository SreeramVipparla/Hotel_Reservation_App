package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
/**
 * @author-VipparlaSreeram
 */
public class HotelResource {

    public static IRoom getRoom(String roomNumber) {
        return ReservationService.getRoom(roomNumber);
    }

    public static Reservation bookRoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = CustomerService.getCustomer(customerEmail);
        return ReservationService.reserveRoom(customer, room, checkInDate, checkOutDate);
    }
    public static void createCustomer(String email, String firstName, String lastName) {
        CustomerService.addCustomer(email, firstName, lastName);
    }

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }


    public static Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = CustomerService.getCustomer(customerEmail);
        return ReservationService.getCustomerReservations(customer);
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return ReservationService.findRooms(checkInDate, checkOutDate);
    }

}
