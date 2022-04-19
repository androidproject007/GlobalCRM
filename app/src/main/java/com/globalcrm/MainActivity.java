package com.globalcrm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.globalcrm.Model.OTP;
import com.globalcrm.Model.OTPWPWR;
import com.globalcrm.Model.OtpResponse;
import com.globalcrm.Other.Datastorage;
import com.globalcrm.Other.MyProgressDialog;
import com.globalcrm.Retrofit.APIClient;
import com.globalcrm.Retrofit.APIInterface;
import com.globalcrm.Model.OTPGSM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    LinearLayout today_history,calendar,month_sale,followup_all,all_otp;
    TextView logoutbtn;
    ImageView logout_image;
    Dialog logout_dailog;
    TextView canclebtn, admin_otp,tm_otp,wr_otp,wp_otp,gsm_otp;
    boolean doubleBackToExitPressedOnce = false;
    ImageView refersh_button,refersh_ws_button,refersh_wr_button;
    MyProgressDialog myProgressDialog;
    List<OTP> otpList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin_otp=(TextView)findViewById(R.id.admin_otp);
        tm_otp=(TextView)findViewById(R.id.tm_otp);
        wr_otp=(TextView)findViewById(R.id.wr_otp);
        wp_otp=(TextView)findViewById(R.id.wp_otp);
        gsm_otp = (TextView) findViewById(R.id.gs_otp);
        refersh_button=(ImageView)findViewById(R.id.refersh_button);
        //otp();
        refersh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //otp();
            }
        });
        refersh_ws_button=(ImageView)findViewById(R.id.refersh_ws_button);
        refersh_ws_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // WhatsOtp();
            }
        });

        refersh_wr_button=(ImageView)findViewById(R.id.refersh_wr_button);
        refersh_wr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // WROtp();
            }
        });

        today_history=(LinearLayout)findViewById(R.id.today_history);
        today_history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,TodayHistory.class);
                startActivity(i);
            }
        });


        all_otp=(LinearLayout)findViewById(R.id.all_otp);
        all_otp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,AllOTP_Activity.class);
                startActivity(i);
            }
        });

        calendar=(LinearLayout)findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,CalendarActivity.class);
                startActivity(i);
            }
        });

        month_sale=(LinearLayout)findViewById(R.id.month_sale);

        month_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,MonthHistory.class);
                startActivity(i);
            }
        });

        followup_all=(LinearLayout)findViewById(R.id.followup_all);
        followup_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(MainActivity.this,FollowupHistory.class);
                startActivity(i);

            }
        });

        logout_image = (ImageView) this.findViewById(R.id.optionlogout);
        logout_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                logout_dailog = new Dialog(new android.view.ContextThemeWrapper(MainActivity.this, R.style.DialogSlideAnim));
                logout_dailog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                logout_dailog.setContentView(R.layout.logout_dialog);
                logout_dailog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                logout_dailog.getWindow().setGravity(Gravity.CENTER);
                logout_dailog.getWindow()
                        .setLayout(
                                ViewGroup.LayoutParams.FILL_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                logoutbtn = (TextView) logout_dailog.findViewById(R.id.logoutbtn);
                logoutbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Datastorage.WritePreference("user_id", null, MainActivity.this);
                        Intent intent = new Intent(MainActivity.this, New_Login_Activity.class);
                        startActivity(intent);
                        finish();
                        logout_dailog.dismiss();
                    }
                });

                canclebtn = (TextView) logout_dailog.findViewById(R.id.canclebtn);
                canclebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logout_dailog.dismiss();
                    }
                });

                logout_dailog.show();
            }
        });
    }

    private void otp(){
        otpList=new ArrayList<>();
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        myProgressDialog = MyProgressDialog.show(MainActivity.this, "", "", true, false, null);
        Call<OtpResponse> call1 = apiInterface.GetOtp();
        call1.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, retrofit2.Response<OtpResponse> response) {
                myProgressDialog.dismiss();
                WhatsOtp();
               if(response.isSuccessful()){
                    Log.d("mytag", "Response +" + response.toString());
                    String rsp = String.valueOf(response.body().getCode());
                    if(rsp.equals("1") == true){
                         otpList= response.body().getOtpList();
                         admin_otp.setText(otpList.get(0).getPassword());
                         tm_otp.setText(otpList.get(1).getPassword());

                         Toast.makeText(MainActivity.this, "Otp Refresh Successfully", Toast.LENGTH_SHORT).show();

                    }if(rsp.equals("0")){

                        Toast.makeText(MainActivity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                   Toast.makeText(MainActivity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                   myProgressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                myProgressDialog.dismiss();
                WhatsOtp();
                Toast.makeText(MainActivity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void WhatsOtp(){
        myProgressDialog = MyProgressDialog.show(MainActivity.this, "", "", true, false, null);
        APIInterface apiInterface = APIClient.getClientTwo().create(APIInterface.class);

        Call<OTPWPWR> call1 = apiInterface.GetwhatsappOtp();
        call1.enqueue(new Callback<OTPWPWR>() {
            @Override
            public void onResponse(Call<OTPWPWR> call, retrofit2.Response<OTPWPWR> response) {
                myProgressDialog.dismiss();
                WROtp();
                if(response.isSuccessful()){
                    Log.d("mytag", "Response +" + response.toString());
                    String rsp = String.valueOf(response.body().getCode());

                  if(rsp.equals("1") == true){
                      wp_otp.setText(response.body().getOtp());
                      Toast.makeText(MainActivity.this, "Otp Refresh Successfully", Toast.LENGTH_SHORT).show();
                 //Toast.makeText(Add_close_activity_new_twoDatabase.this, "Add Data Successfully", Toast.LENGTH_SHORT).show();
                  }

                  if(rsp.equals("0")){
                    Toast.makeText(MainActivity.this, "Please Refersh OTP", Toast.LENGTH_SHORT).show();
                  }

                } else {
                    Toast.makeText(MainActivity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<OTPWPWR> call, Throwable t) {
                myProgressDialog.dismiss();
                WROtp();
                Toast.makeText(MainActivity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void WROtp(){
        myProgressDialog = MyProgressDialog.show(MainActivity.this, "", "", true, false, null);
        APIInterface apiInterface = APIClient.getClientThree().create(APIInterface.class);

        Call<OTPWPWR> call1 = apiInterface.GetROtp();
        call1.enqueue(new Callback<OTPWPWR>() {
            @Override
            public void onResponse(Call<OTPWPWR> call, retrofit2.Response<OTPWPWR> response) {
                myProgressDialog.dismiss();
                GSMOTP();
                if(response.isSuccessful()){
                    Log.d("mytag", "Response +" + response.toString());
                    String rsp = String.valueOf(response.body().getCode());

                  if(rsp.equals("1") == true){
                      wr_otp.setText(response.body().getOtp());
                      Toast.makeText(MainActivity.this, "Otp Refresh Successfully", Toast.LENGTH_SHORT).show();
                 //Toast.makeText(Add_close_activity_new_twoDatabase.this, "Add Data Successfully", Toast.LENGTH_SHORT).show();
                  }

                  if(rsp.equals("0")){
                    Toast.makeText(MainActivity.this, "Please Refersh OTP", Toast.LENGTH_SHORT).show();
                  }

                } else {
                    Toast.makeText(MainActivity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<OTPWPWR> call, Throwable t) {
                myProgressDialog.dismiss();
                GSMOTP();
                Toast.makeText(MainActivity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private  void GSMOTP(){
        myProgressDialog = MyProgressDialog.show(MainActivity.this, "", "", true, false, null);
        APIInterface apiInterface = APIClient.getClientFour().create(APIInterface.class);
        Call<OTPWPWR> call1 = apiInterface.GetGSM();
        call1.enqueue(new Callback<OTPWPWR>() {
            @Override
            public void onResponse(Call<OTPWPWR> call, retrofit2.Response<OTPWPWR> response) {
                myProgressDialog.dismiss();

                if(response.isSuccessful()){
                    Log.d("mytag", "Response +" + response.toString());
                    String rsp = String.valueOf(response.body().getCode());

                    if(rsp.equals("1") == true){
                        gsm_otp.setText(response.body().getOtp());
                        Toast.makeText(MainActivity.this, "Otp Refresh Successfully", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Add_close_activity_new_twoDatabase.this, "Add Data Successfully", Toast.LENGTH_SHORT).show();
                    }

                    if(rsp.equals("0")){
                        Toast.makeText(MainActivity.this, "Please Refersh OTP", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<OTPWPWR> call, Throwable t) {
                myProgressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
