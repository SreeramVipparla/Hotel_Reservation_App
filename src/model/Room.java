package model;

import java.util.Objects;
/**
 * @author-VipparlaSreeram
 */
public class Room  implements IRoom {

    protected String roomNumber;
    protected Double price;
    protected RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }
    public String toString() {
        return "Room number: " + this.roomNumber + " " + this.enumeration + " bed room Price: $" + this.price;
    }
    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber) && price.equals(room.price) && enumeration == room.enumeration;
    }

    @Override
    public Boolean isFree() {
        if (this.price == (double) 0) {
            return true;
        }
        return false;
    }
    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }
    @Override
    public Double getRoomPrice() {
        return this.price;
    }
    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, enumeration);
    }
}
