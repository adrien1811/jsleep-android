package AdrienArdraRamadhanJSleepMN.jsleep_android;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Facility;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Room;
//detailRoom class
/**
 * this class is used to show detail room in listview
 *
 * @author Adrien Ardra
 * @version 1.0
 *
 */
public class DetailRoomActivity extends AppCompatActivity {
    Context mContext;
    protected static Room RoomDetail = MainActivity.roomList.get(MainActivity.room_id);
    TextView dtlname, dtlbedtype, dtlsize, dtlprice, dtladdress;
    CheckBox cbac, cbrefrigerator, cbwifi, cbbathtub, cbbalcony, cbrestaurant, cbswimmingpool, cbfitnesscenter;
    List<Facility> facilities = MainActivity.roomList.get(MainActivity.room_id).facility;
    Button btnbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}
        setContentView(R.layout.activity_detail_room);
        cbac = findViewById(R.id.ACDetail);
        dtlname = findViewById(R.id.NameDetailInfo);
        dtlbedtype = findViewById(R.id.bedTypeDetailInfo);
        dtlsize = findViewById(R.id.sizeDetailInfo);
        dtlprice = findViewById(R.id.priceDetailInfo);
        dtladdress = findViewById(R.id.addressDetailInfo);
        cbwifi = findViewById(R.id.WifiDetail);
        cbbathtub = findViewById(R.id.BathubDetail);
        cbrefrigerator = findViewById(R.id.RefrigeratorDetail);
        cbbalcony = findViewById(R.id.BalconyDetail);
        cbswimmingpool = findViewById(R.id.SwimmingPoolDetail);
        cbrestaurant = findViewById(R.id.RestaurantDetail);
        cbfitnesscenter = findViewById(R.id.FitnessCenterDetail);
        dtladdress.setText(RoomDetail.address);
        dtlname.setText(RoomDetail.name);
        String price = "Rp. " + String.valueOf(RoomDetail.price.price);
        dtlprice.setText(price);
        String size = String.valueOf(RoomDetail.size) + " m";
        dtlsize.setText(size);
        String bed = RoomDetail.bedType.toString() + " bed";
        dtlbedtype.setText(bed);

        for(Facility facility : facilities){
            if(facility == Facility.AC){
                cbac.setChecked(true);
            } else if(facility == Facility.Refrigerator){
                cbrefrigerator.setChecked(true);
            } else if(facility == Facility.WiFi){
                cbwifi.setChecked(true);
            } else if(facility == Facility.Bathtub){
                cbbathtub.setChecked(true);
            } else if(facility == Facility.Balcony){
                cbbalcony.setChecked(true);
            } else if(facility == Facility.Restaurant){
                cbrestaurant.setChecked(true);
            } else if(facility == Facility.SwimmingPool){
                cbswimmingpool.setChecked(true);
            } else if(facility == Facility.FitnessCenter){
                cbfitnesscenter.setChecked(true);
            }
        }
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(DetailRoomActivity.this, MainActivity.class);
                startActivity(move);
            }
        });
    }
}