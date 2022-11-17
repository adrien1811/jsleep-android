package AdrienArdraRamadhanJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import android.os.Bundle;
import android.content.Intent;
import android.view.*;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.*;
import java.io.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    String name;
    static ArrayList<Room> roomList = new ArrayList<Room>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream filepath = null;
        try {
            filepath = getAssets().open("randomRoomList.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(filepath));

            Gson gson = new Gson();
            Room[] temp = gson.fromJson(reader, Room[].class);
            Collections.addAll(roomList, temp);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> names = new ArrayList<String>();
        for (Room room : roomList) {
            names.add(room.name);
        }

        ArrayAdapter<String > arrayAdapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                names );
        ListView lv = (ListView) findViewById(R.id.list_View);
        lv.setAdapter(arrayAdapter);

    }
    MenuItem item;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.person:
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
        }
        return true;
    }
}
