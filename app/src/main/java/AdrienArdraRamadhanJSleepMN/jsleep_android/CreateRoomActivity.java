package AdrienArdraRamadhanJSleepMN.jsleep_android;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.content.Context;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.*;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * this class is used to create room
 *
 * @author Adrien Ardra
 * @version 1.0
 *
 */
public class CreateRoomActivity extends AppCompatActivity {
    Context mContext;
    BaseApiService mBaseApiService;
    Button create, cancel;
    Spinner bedSpinner,citySpinner;
    EditText CR_name,CR_address,CR_price,CR_size;
    ArrayList<Facility> facility = new ArrayList<Facility>();
    CheckBox AC,WiFi,SwimmingPool,FitnessCenter,Balcony,Restaurant,Refrigerator;
    BedType bedType;
    City city;
    Price price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mBaseApiService = UtilsApi.getApiService();
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        bedSpinner = (Spinner) findViewById(R.id.bed_spinner);
        citySpinner = (Spinner) findViewById(R.id.city_spinner);

        AC = findViewById(R.id.ACcb);
        WiFi = findViewById(R.id.Wificb);
        SwimmingPool = findViewById(R.id.SwimmingPoolcb);
        FitnessCenter = findViewById(R.id.FitnessCentercb);
        Balcony = findViewById(R.id.Balconycb);
        Restaurant = findViewById(R.id.Restaurantcb);
        Refrigerator = findViewById(R.id.Refrigeratorcb);

        CR_name = findViewById(R.id.NameRoom);
        CR_address = findViewById(R.id.AddressRoom);
        CR_price = findViewById(R.id.PriceRoom);
        CR_size = findViewById(R.id.SizeRoom);

        create = findViewById(R.id.Book);
        cancel = findViewById(R.id.CancelBook);

        bedSpinner.setAdapter(new ArrayAdapter<BedType>(this, android.R.layout.simple_spinner_item, BedType.values()));
        citySpinner.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City.values()));


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SwimmingPool.isChecked()) {
                    facility.add(Facility.SwimmingPool);
                }
                if (Restaurant.isChecked()) {
                    facility.add(Facility.Restaurant);
                }
                if (Refrigerator.isChecked()) {
                    facility.add(Facility.Refrigerator);
                }
                if (FitnessCenter.isChecked()) {
                    facility.add(Facility.FitnessCenter);
                }
                if (Balcony.isChecked()) {
                    facility.add(Facility.Balcony);
                }
                if (WiFi.isChecked()) {
                    facility.add(Facility.WiFi);
                }
                if (AC.isChecked()) {
                    facility.add(Facility.AC);
                }

                String typeof_bed = bedSpinner.getSelectedItem().toString();
                String name_city = citySpinner.getSelectedItem().toString();
                bedType = BedType.valueOf(typeof_bed);
                city = City.valueOf(name_city);

                //make an object of room's price & size
                Integer objPrice = new Integer(CR_price.getText().toString());
                Integer objSize = new Integer(CR_size.getText().toString());

                //int type
                int roomPrice = objPrice.parseInt(CR_price.getText().toString());
                int roomSize = objSize.parseInt(CR_size.getText().toString());
                Room room = requestRoom(LoginActivity.staticAcc.id, CR_name.getText().toString(), roomPrice, CR_address.getText().toString(), roomSize, facility, city, bedType);

            }
        });

    }
    protected Room requestRoom(int id, String name, int price, String address, int size, ArrayList<Facility> facility, City city, BedType bedType) {
        mBaseApiService.room(id, name, price, address, size, facility, city, bedType).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if(response.isSuccessful()){
                    Toast.makeText(mContext, "Room has successfully created!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateRoomActivity.this, MainActivity.class);
                    startActivity(intent);
                    System.out.println("SUCCESS");
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                Toast.makeText(mContext, "Failed!", Toast.LENGTH_SHORT).show();
                System.out.println("FAILED!!");
            }
        });
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addbox:
                Intent intent = new Intent(CreateRoomActivity.this, AboutMeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}