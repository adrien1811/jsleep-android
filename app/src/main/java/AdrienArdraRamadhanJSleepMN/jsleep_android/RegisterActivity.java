package AdrienArdraRamadhanJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Account;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.BaseApiService;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText username,password;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);

        mApiService = UtilsApi.getApiService();
        mContext = this;
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    protected Account requestRegister(){
        mApiService.login(username.getText().toString(), password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    Toast.makeText(mContext, "Regiter Successfully!!", Toast.LENGTH_SHORT).show();
                    account = response.body();
                    System.out.println(account.toString());
                    Intent move = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(move);
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext, "Account Already Registered!!", Toast.LENGTH_SHORT).show();
                System.out.println("Account Already Registered!!!");
            }
        });
        return null;
    }
}