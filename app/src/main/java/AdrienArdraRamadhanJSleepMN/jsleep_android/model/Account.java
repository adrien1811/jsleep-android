package AdrienArdraRamadhanJSleepMN.jsleep_android.model;

import java.util.ArrayList;
import java.util.Date;

public class Account extends Serializable{
    public String name;
    public double balance;
    public String email;
    public String password;
    public String renter;

    public Account(int id) {
        super(id);
    }

    @Override
    public String toString(){
        return"Account{" +
                "balance=" + balance +
                ", email='" + email+ '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", renter=" + renter + '}';



    }
}
