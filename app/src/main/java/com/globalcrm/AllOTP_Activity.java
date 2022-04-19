package com.globalcrm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.globalcrm.Model.OTP;
import com.globalcrm.Model.OTPWPWR;
import com.globalcrm.Model.OtpResponse;
import com.globalcrm.Other.MyProgressDialog;
import com.globalcrm.Retrofit.APIClient;
import com.globalcrm.Retrofit.APIInterface;
import com.globalcrm.Model.OTPGSM;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public  class AllOTP_Activity extends AppCompatActivity {
    TextView admin_otp,tm_otp,wr_otp,wp_otp,gsm_otp;
    ImageView refersh_button,refersh_ws_button,refersh_wr_button,refersh_wr_button1;
    MyProgressDialog myProgressDialog;
    List<OTP> otpList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_otp);

        admin_otp=(TextView)findViewById(R.id.admin_otp);
        tm_otp=(TextView)findViewById(R.id.tm_otp);
        wr_otp=(TextView)findViewById(R.id.wr_otp);
        wp_otp=(TextView)findViewById(R.id.wp_otp);
        gsm_otp = (TextView) findViewById(R.id.gs_otp);
        otp();
        refersh_button=(ImageView)findViewById(R.id.refersh_button);
        refersh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otp();
            }
        });

        refersh_ws_button=(ImageView)findViewById(R.id.refersh_ws_button);
        refersh_ws_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WhatsOtp();
            }
        });

        refersh_wr_button=(ImageView)findViewById(R.id.refersh_wr_button);
        refersh_wr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WROtp();
            }
        });
        refersh_wr_button1=(ImageView)findViewById(R.id.refersh_gsm_button1);
        refersh_wr_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GSMOTP();
            }
        });
    }


    private void otp(){
        otpList=new ArrayList<>();
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        myProgressDialog = MyProgressDialog.show(AllOTP_Activity.this, "", "", true, false, null);
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
                        Toast.makeText(AllOTP_Activity.this, "Otp Refresh Successfully", Toast.LENGTH_SHORT).show();

                    }if(rsp.equals("0")){
                        Toast.makeText(AllOTP_Activity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AllOTP_Activity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                    myProgressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                myProgressDialog.dismiss();
                WhatsOtp();
                Toast.makeText(AllOTP_Activity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void WhatsOtp(){
        myProgressDialog = MyProgressDialog.show(AllOTP_Activity.this, "", "", true, false, null);
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
                        Toast.makeText(AllOTP_Activity.this, "Otp Refresh Successfully", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Add_close_activity_new_twoDatabase.this, "Add Data Successfully", Toast.LENGTH_SHORT).show();
                    }

                    if(rsp.equals("0")){
                        Toast.makeText(AllOTP_Activity.this, "Please Refersh OTP", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(AllOTP_Activity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<OTPWPWR> call, Throwable t) {
                myProgressDialog.dismiss();
                WROtp();
                Toast.makeText(AllOTP_Activity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void WROtp(){
        myProgressDialog = MyProgressDialog.show(AllOTP_Activity.this, "", "", true, false, null);
        APIInterface apiInterface = APIClient.getClientThree().create(APIInterface.class);
        Call<OTPWPWR> call1 = apiInterface.GetROtp();
        call1.enqueue(new Callback<OTPWPWR>() {
            @Override
            public void onResponse(Call<OTPWPWR> call, retrofit2.Response<OTPWPWR> response) {
                myProgressDialog.dismiss();

                if(response.isSuccessful()){
                    Log.d("mytag", "Response +" + response.toString());
                    String rsp = String.valueOf(response.body().getCode());

                    if(rsp.equals("1") == true){
                        wr_otp.setText(response.body().getOtp());
                        GSMOTP();
                        Toast.makeText(AllOTP_Activity.this, "Otp Refresh Successfully", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Add_close_activity_new_twoDatabase.this, "Add Data Successfully", Toast.LENGTH_SHORT).show();
                    }

                    if(rsp.equals("0")){
                        Toast.makeText(AllOTP_Activity.this, "Please Refersh OTP", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(AllOTP_Activity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<OTPWPWR> call, Throwable t) {
                myProgressDialog.dismiss();
                GSMOTP();
                Toast.makeText(AllOTP_Activity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private  void GSMOTP(){
        myProgressDialog = MyProgressDialog.show(AllOTP_Activity.this, "", "", true, false, null);
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
                        Toast.makeText(AllOTP_Activity.this, "Otp Refresh Successfully", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Add_close_activity_new_twoDatabase.this, "Add Data Successfully", Toast.LENGTH_SHORT).show();
                    }

                    if(rsp.equals("0")){
                        Toast.makeText(AllOTP_Activity.this, "Please Refersh OTP", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(AllOTP_Activity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<OTPWPWR> call, Throwable t) {
                myProgressDialog.dismiss();
                Toast.makeText(AllOTP_Activity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
