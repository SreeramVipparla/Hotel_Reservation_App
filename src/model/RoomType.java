package model;

import java.util.HashMap;
import java.util.Map;
/**
 * @author-VipparlaSreeram
 */
public enum RoomType {
    SINGLE (1),
    DOUBLE (2);

    private int numberOfBeds;
    RoomType(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    private static final Map<Integer, RoomType> No_Of_Beds = new HashMap<Integer, RoomType>();
    
    public static RoomType valueforNumberOfBeds(int numberOfBeds) {
        return No_Of_Beds.get(numberOfBeds);
    }
    static {
        for (RoomType roomType : values()) {
            No_Of_Beds.put(roomType.numberOfBeds, roomType);
        }
    }





}
