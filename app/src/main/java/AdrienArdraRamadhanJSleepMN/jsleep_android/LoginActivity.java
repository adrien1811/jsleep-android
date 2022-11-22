package AdrienArdraRamadhanJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Account;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.BaseApiService;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText username, password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mApiService = UtilsApi.getApiService();
        mContext = this;
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);

        TextView register = findViewById(R.id.register);
        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();

                String pass = password.getText().toString();
                Account account = requestLogin();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(move);
            }

        });
    }

    protected Account requestAccount() {
        mApiService.getAccount(0).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Account account;
                    account = response.body();
                    System.out.println("SUCCESSFUL");
                    System.out.println(account.toString());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("failed");
                System.out.println(t.toString());
                Toast.makeText(mContext, "no Account id=0", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
    protected Account requestLogin(){
        mApiService.login(username.getText().toString(), password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    Toast.makeText(mContext, "Login Successfully!", Toast.LENGTH_SHORT).show();
                    account = response.body();
                    System.out.println(account.toString());
                    Intent log = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(log);
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext, "Invalid Email!", Toast.LENGTH_SHORT).show();
                System.out.println("Invalid Email or Password!");
            }
        });
        return null;
    }
}