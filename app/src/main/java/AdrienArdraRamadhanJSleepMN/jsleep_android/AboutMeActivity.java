package AdrienArdraRamadhanJSleepMN.jsleep_android;
import static AdrienArdraRamadhanJSleepMN.jsleep_android.R.id.AddressRenterInfo;
import static AdrienArdraRamadhanJSleepMN.jsleep_android.R.id.Cardview1;
import static AdrienArdraRamadhanJSleepMN.jsleep_android.R.id.NameRenterInfo;
import static AdrienArdraRamadhanJSleepMN.jsleep_android.R.id.PhoneNumberRenterInfo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Context;
import android.os.Bundle;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.RetrofitClient;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Account;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Renter;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.BaseApiService;
import java.sql.SQLOutput;
/**
 * About Me Activity represent the summary of profile activity of this application.
 * This section will display such as name, email, and balance of user.
 * This activity should be the same section to Top Up user's balance.
 *
 * @author Adrien Ardra
 * @version 1.0
 *
 */
public class AboutMeActivity extends AppCompatActivity {
    BaseApiService mBaseApiService;
    Context mContext;
    TextView name;
    TextView balance;
    EditText Amount;
    Button RegisterRenter;
    CardView inputLayout, infoLayout;
    Button TopUp;
    TextView nameRenter,addressRenter,phoneRenter;
    LinearLayout registerRenter;
    EditText nameInsert,addressInsert,phoneInsert;
    Button Cancel;
    Button Register;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        mBaseApiService = UtilsApi.getApiService();
        mContext = this;
        name = findViewById(R.id.NameInfo);
        email = findViewById(R.id.EmailInfo);
        balance = findViewById(R.id.BalanceInfo);
        registerRenter = findViewById(R.id.RegisterRenterLayout);
        name.setText(LoginActivity.staticAcc.name);
        email.setText(LoginActivity.staticAcc.email);
        balance.setText(String.valueOf(LoginActivity.staticAcc.balance));
        TopUp = findViewById(R.id.TopUp);
        Amount = findViewById(R.id.Amount);
        TopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(LoginActivity.staticAcc.id);
                System.out.println(Double.parseDouble(Amount.getText().toString()));
                TopUp(LoginActivity.staticAcc.id, Double.parseDouble(Amount.getText().toString()));
            }
        });
        RegisterRenter = findViewById(R.id.RegisterRenter);
        //cardview untuk memasukan input renter
        inputLayout = findViewById(R.id.Cardview1);
        nameInsert = findViewById(R.id.NameRegisterRenter);
        addressInsert =findViewById(R.id.AddressRegisterRenter);
        phoneInsert = findViewById(R.id.PhoneRegisterRenter);
        Register = findViewById(R.id.RegButton);
        Cancel = findViewById(R.id.CancelButton);

        //cardview untuk mendisplay info renter
        infoLayout = findViewById(R.id.Cardview2);
        nameRenter = findViewById(R.id.NameRenterInfo);
        addressRenter = findViewById(R.id.AddressRenterInfo);
        phoneRenter = findViewById(R.id.PhoneNumberRenterInfo);
        RegisterRenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputLayout.setVisibility(View.VISIBLE);
                infoLayout.setVisibility(View.GONE);
            }
        });
        if(LoginActivity.staticAcc.renter == null) {
            RegisterRenter.setVisibility(View.VISIBLE);
            inputLayout.setVisibility(View.INVISIBLE);
            infoLayout.setVisibility(View.INVISIBLE);
        }
        else {
            inputLayout.setVisibility(View.GONE);
            infoLayout.setVisibility(View.VISIBLE);
            nameRenter.setText(LoginActivity.staticAcc.renter.username);
            addressRenter.setText(LoginActivity.staticAcc.renter.address);
            phoneRenter.setText(LoginActivity.staticAcc.renter.phoneNumber);
        }
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterRenter.setVisibility(View.INVISIBLE);
                inputLayout.setVisibility(View.INVISIBLE);
                infoLayout.setVisibility(View.VISIBLE);
                Renter accountRenter = registerRenter(LoginActivity.staticAcc.id, nameInsert.getText().toString(), addressInsert.getText().toString(), phoneInsert.getText().toString());
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterRenter.setVisibility(View.VISIBLE);
                infoLayout.setVisibility(View.VISIBLE);
                inputLayout.setVisibility(View.INVISIBLE);
            }
        });
    }
    protected Renter registerRenter(int id, String username, String address, String phoneNumber ) throws NullPointerException {
        System.out.println("Id: " + id);
        System.out.println("Username: " + username);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phoneNumber);
        mBaseApiService.registerRenter(id, username, address, phoneNumber).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                if(response.isSuccessful()){
                    LoginActivity.staticAcc.renter = response.body();
                    Intent intent = new Intent(AboutMeActivity.this,AboutMeActivity.class);
                    startActivity(intent);
                    System.out.println("SUCCESFULLY REGISTER") ;
                    Toast.makeText(mContext, "Renter Registered!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Renter> call, Throwable t) {
                Toast.makeText(mContext, "FAILED REGISTERED!", Toast.LENGTH_SHORT).show();
                System.out.println("FAILED REGISTER") ;
            }
        });
        return null;
    }
    protected Boolean TopUp(int id, double balance ){
        mBaseApiService.topUp(id,balance).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    Boolean display_topUp = response.body();
                    System.out.println("SUCESSFULLY TOP UP") ;
                    Toast.makeText(mContext, "SUCESSFULLY TOP UP", Toast.LENGTH_SHORT).show();
                    LoginActivity.staticAcc.balance = LoginActivity.staticAcc.balance + balance;
                    recreate();
                    System.out.println("SUCCESFUL");
                    Intent intent = new Intent(AboutMeActivity.this, AboutMeActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println("TOP UP FAILED!");
                System.out.println(t.toString());
                Toast.makeText(mContext,"Top Up Failed",Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        if (LoginActivity.staticAcc.renter != null) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.topmenu, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addbox) {
            Intent intent = new Intent(AboutMeActivity.this, CreateRoomActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}