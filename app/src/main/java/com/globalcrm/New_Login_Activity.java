package com.globalcrm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.globalcrm.Model.LoginResponse;
import com.globalcrm.Other.CheckNetwork;
import com.globalcrm.Other.Datastorage;
import com.globalcrm.Other.MyProgressDialog;
import com.globalcrm.Retrofit.APIClient;
import com.globalcrm.Retrofit.APIInterface;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by ADMIJN on 24-Apr-18.
 */

public class New_Login_Activity extends AppCompatActivity {

    EditText txt_Loginusername,txt_LoginPassword;
    String username,pwd;
    Button btn_Login;
    MyProgressDialog myProgressDialog;
    String Ex_id;
    String token;
    TextView resend;
    String usernameintent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        resend=(TextView)findViewById(R.id.resend);
        Bundle bundle = getIntent().getExtras();
        usernameintent = bundle.getString("username");

       // FirebaseMessaging.getInstance().setAutoInitEnabled(true);
       // token = FirebaseInstanceId.getInstance().getToken();
        Log.d("mytag","token"+token);

        Ex_id = (String) Datastorage.ReadFromPreference("user_id", Datastorage.STRING_KEY, New_Login_Activity.this);
        if(Ex_id != null){
            if (!Ex_id.equalsIgnoreCase("")) {
             //   Toast.makeText(this, ""+ Ex_id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(New_Login_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        txt_Loginusername = (EditText) this.findViewById(R.id.txt_Loginusername);
        if(!usernameintent.isEmpty()){
            txt_Loginusername.setText(usernameintent);
        }
        txt_LoginPassword = (EditText) this.findViewById(R.id.txt_LoginPassword);

        btn_Login = (Button) this.findViewById(R.id.btn_Login);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = (txt_Loginusername).getText().toString().trim();
                pwd = (txt_LoginPassword).getText().toString().trim();
                if (username.equals("")) {
                    Toast.makeText(New_Login_Activity.this, "Please enter your register name", Toast.LENGTH_SHORT).show();
                } else if (pwd.equals("")) {
                        Toast.makeText(New_Login_Activity.this, "Please enter your valid password", Toast.LENGTH_SHORT).show();
                 } else {
                    if(CheckNetwork.isInternetAvailable(New_Login_Activity.this)){
                        loginActivity();
                    }else {
                        Toast.makeText(New_Login_Activity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!usernameintent.isEmpty()){
                    ResendOtp();
                }
            }
        });
    }


    private void loginActivity(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        myProgressDialog = MyProgressDialog.show(New_Login_Activity.this, "", "", true, false, null);
        Call<LoginResponse> call1 = apiInterface.Login(username, pwd);
        call1.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                myProgressDialog.dismiss();

                if(response.isSuccessful()){
                    Log.d("mytag", "Response +" + response.toString());
                    String rsp = String.valueOf(response.body().getCode());
                    if(rsp.equals("1") == true){
                        Toast.makeText(New_Login_Activity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Datastorage.WritePreference("user_id", response.body().getId(), New_Login_Activity.this);
                        Intent i = new Intent(New_Login_Activity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }if(rsp.equals("0")){
                        Toast.makeText(New_Login_Activity.this, "User name Or Password did not match.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(New_Login_Activity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                    myProgressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                myProgressDialog.dismiss();
                Toast.makeText(New_Login_Activity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ResendOtp(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        myProgressDialog = MyProgressDialog.show(New_Login_Activity.this, "", "", true, false, null);
        Call<LoginResponse> call1 = apiInterface.Loginotp(usernameintent);
        call1.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                myProgressDialog.dismiss();

                if(response.isSuccessful()){
                    Log.d("mytag", "Response +" + response.toString());
                    String rsp = String.valueOf(response.body().getCode());

                    if(rsp.equals("1") == true){
                        Toast.makeText(New_Login_Activity.this, "Otp Send Your Mobile Successfully", Toast.LENGTH_SHORT).show();
                        // Datastorage.WritePreference("user_id", response.body().getId(), New_OTP_Activity.this);
//                        Intent i = new Intent(New_Login_Activity.this, New_Login_Activity.class);
//                        startActivity(i);
//                        finish();

                    }if(rsp.equals("0")){
                        Toast.makeText(New_Login_Activity.this, "User name did not match.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(New_Login_Activity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                    myProgressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                myProgressDialog.dismiss();
                Toast.makeText(New_Login_Activity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
            }
        });
    }
}