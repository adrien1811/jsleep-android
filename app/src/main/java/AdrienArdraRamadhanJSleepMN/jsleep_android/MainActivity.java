package AdrienArdraRamadhanJSleepMN.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Account;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Room;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.BaseApiService;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static Account LoginAccount;
    public static Room RoomDetail;
    protected static Account RegisterAccount;
    Context mContext;
    BaseApiService mApiService;
    public static int room_id;
    EditText pagenumber;
    Button next, prev, go;
    public static List<Room> roomList;
    ListView listView;
    List<Room> room;
    int page = 1;
    static ArrayList<Room> rooms = new ArrayList<>();
    int pageSize = 10;
    String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mApiService = UtilsApi.getApiService();
        requestRoom();
        next = findViewById(R.id.nextb);
        prev = findViewById(R.id.prevb);
        go = findViewById(R.id.gob);
        listView = findViewById(R.id.list_view);
        pagenumber = findViewById(R.id.pageList);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;
                System.out.println(page);
                pagenumber.setText(String.valueOf(page));
                requestRoom();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page > 1){
                    page--;
                    System.out.println(page);
                    pagenumber.setText(String.valueOf(page));
                    requestRoom();

                }else if(page == 1){
                    Toast.makeText(mContext, "Cannot prev on first page", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                room_id = i;
                Intent move = new Intent(MainActivity.this, DetailRoomActivity.class);
                startActivity(move);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(pagenumber.getText().toString()) >= 0){
                    page = Integer.parseInt(pagenumber.getText().toString());
                    System.out.println(page);
                    requestRoom();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.person:
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
                return true;
            case R.id.addbox:
                Intent intent2 = new Intent(this, CreateRoomActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        if(LoginActivity.staticAcc.renter == null){
            menu.findItem(R.id.addbox).setVisible(false);
        }
        return true;
    }

    protected List<Room> requestRoom(){
        pagenumber = findViewById(R.id.pageList);
        listView = findViewById(R.id.list_view);
        String psizestr = pagenumber.getText().toString();
        mApiService.getAllRoom(page - 1, pageSize).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if(response.isSuccessful()){
                    ArrayList<String> ListId = new ArrayList<>();
                    roomList = response.body();
                    for(Room room : roomList){
                        ListId.add(room.name);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, ListId);
                    listView.setAdapter(adapter);
                    Toast.makeText(mContext, "Display Room Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                Toast.makeText(mContext, "Failed to display Room", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}