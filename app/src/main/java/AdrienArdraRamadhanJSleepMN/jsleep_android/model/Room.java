package AdrienArdraRamadhanJSleepMN.jsleep_android.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Room class
 * This class is used to represent a room
 * @author Adrien Ardra
 * @see Serializable
 */
public class Room {
    public int accountId;
    public String name;
    public ArrayList<Date> booked;
    public String address;
    public Price price;
    public City city;
    public int size;
    public BedType bedType;
    public List<Facility> facility;
}
