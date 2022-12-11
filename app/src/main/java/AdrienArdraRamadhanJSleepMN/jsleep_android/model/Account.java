package AdrienArdraRamadhanJSleepMN.jsleep_android.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

/**
 * this class is used to create room
 *
 * @author Adrien Ardra
 * @version 1.0
 *
 */
public class Account extends Serializable{
    public String name;
    public String password;
    public Renter renter;
    public String email;
    public double balance;

    public Account(int id) {
        super(id);
    }
    @NonNull
    @Override
    public String toString(){
        return "Account{" +
                "balance=" + balance +
                ", email" + email + '\'' +
                ", name=" + name + '\'' +
                ", password" + password + '\'' +
                ", renter=" + renter + '\'' +
                '}';
    }


}
