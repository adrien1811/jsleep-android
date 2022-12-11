package AdrienArdraRamadhanJSleepMN.jsleep_android.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
