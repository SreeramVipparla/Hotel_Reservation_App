package model;

import java.util.Date;
import java.util.Objects;

public class Reservation {
/**
 * @author-VipparlaSreeram
 */
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public Customer getCustomer() {
        return this.customer;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }


    public IRoom getRoom() {
        return this.room;
    }

    public boolean isRoomReserved(Date checkInDate, Date checkOutDate) {
        if (checkInDate.before(this.checkOutDate) && checkOutDate.after(this.checkInDate)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Reservation for " + this.customer +
                ", " + room +
                ", Check-In on: " + this.checkInDate +
                ", Check-Out on: " + this.checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return customer.equals(that.customer) && room.equals(that.room) && checkInDate.equals(that.checkInDate) && checkOutDate.equals(that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }
}
