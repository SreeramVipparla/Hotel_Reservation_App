package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;

/**
 * @author-VipparlaSreeram
 */
public class AdminResource {

    public static void addRoom(IRoom room) {
        ReservationService.addRoom(room);
    }
    public static Collection<IRoom> getAllRooms() {
        return ReservationService.getAllRooms();
    }
    public static Collection<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static Collection<Reservation> getAllReservations() { return ReservationService.getAllReservations(); }

}
