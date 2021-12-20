package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
/**
 * @author-VipparlaSreeram
 */
public class ReservationService {

    private static final Map<String, IRoom> roomMap = new HashMap<String, IRoom>();
    private static final Map<String, Collection<Reservation>> reservationMap = new HashMap<String, Collection<Reservation>>();

    public static void addRoom(IRoom room) {
        roomMap.put(room.getRoomNumber(), room);
    }

    public static IRoom getRoom(String roomNumber) {
        return roomMap.get(roomNumber);
    }

    public static Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {

        if (isRoomReserved(room, checkInDate, checkOutDate)) {
            return null;
        }
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customerReservations = getCustomerReservations(customer);
        if (customerReservations == null) {
            customerReservations = new LinkedList<>();
        }
        customerReservations.add(reservation);
        reservationMap.put(customer.getEmail(), customerReservations);
        return reservation;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        Collection<IRoom> reservedRooms = getAllReservedRooms(checkInDate, checkOutDate);
        Collection<IRoom> availableRooms = new LinkedList<>();
        for (IRoom room : getAllRooms()) {
            if (!reservedRooms.contains(room)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public static Collection<Reservation> getCustomerReservations(Customer customer) {
        return reservationMap.get(customer.getEmail());
    }

    public static Collection<Reservation> getAllReservations() {
        Collection<Reservation> allReservations = new LinkedList<>();
        for (Collection<Reservation> customerReservations : reservationMap.values()) {
            allReservations.addAll(customerReservations);
        }
        return allReservations;
    }

    public static Collection<IRoom> getAllRooms() {
        return roomMap.values();
    }

    private static Collection<IRoom> getAllReservedRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> reservedRooms = new LinkedList<>();
        for (Reservation reservation : getAllReservations()) {
            if (reservation.isRoomReserved(checkInDate, checkOutDate)) {
                reservedRooms.add(reservation.getRoom());
            }
        }
        return reservedRooms;
    }

    private static boolean isRoomReserved(IRoom room, Date checkInDate, Date checkOutDate) {
        Collection<IRoom> reservedRooms = getAllReservedRooms(checkInDate, checkOutDate);
        if (reservedRooms.contains(room)) {
            return true;
        }
        return false;
    }

}
