package AdrienArdraRamadhanJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Account;

public class AboutMeActivity extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        balance = findViewById(R.id.balance);
        name.setText(LoginActivity.staticAcc.name);
        email.setText(LoginActivity.staticAcc.email);
        balance.setText(String.valueOf(LoginActivity.staticAcc.balance));
    }
}