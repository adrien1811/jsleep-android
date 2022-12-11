package AdrienArdraRamadhanJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Account;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import AdrienArdraRamadhanJSleepMN.jsleep_android.request.BaseApiService;


/**
 * this class is used to register account to app
 *
 * @author Adrien Ardra
 * @version 1.0
 *
 */
public class RegisterActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    EditText name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        email = findViewById(R.id.EmailLogin);
        name = findViewById(R.id.UsernameLogin);
        password = findViewById(R.id.PasswordLogin);

        Button register = findViewById(R.id.Registerlogin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_register = name.getText().toString();
                String email_register = email.getText().toString();
                String pass_register = password.getText().toString();
                Account account_register = requestRegister(name_register, email_register, pass_register);
            }
        });
    }

    protected Account requestRegister(String name, String email, String password){
        mApiService.register(name, email, password).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    account = response.body();
                    System.out.println("REGISTER SUCCESSFUL!");
                    System.out.println(account.toString());
                    Toast.makeText(mContext, "Account registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("REGISTER FAILED!!");
                Toast.makeText(mContext, "Account has already registered", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

}