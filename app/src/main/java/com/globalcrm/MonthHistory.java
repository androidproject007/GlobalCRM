package com.globalcrm;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.globalcrm.Adapter.excutiveAdapter;
import com.globalcrm.Model.Executivelist;
import com.globalcrm.Model.ResponseAllTodayAppoitment;
import com.globalcrm.Model.ResponseExecutive;
import com.globalcrm.Model.TodayApoList;
import com.globalcrm.Other.CheckNetwork;
import com.globalcrm.Other.MyProgressDialog;
import com.globalcrm.Retrofit.APIClient;
import com.globalcrm.Retrofit.APIInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class MonthHistory extends AppCompatActivity {
    MyProgressDialog myProgressDialog;
    RecyclerView recyclerview_excutive_list;
    RecyclerView.LayoutManager layoutManager;
    boolean isOnTextChanged = false;
    List<Executivelist> executivelists= new ArrayList<>();
    EditText search;
    TextView  nottext;
    LinearLayout lv_linear;
    excutiveAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_activity);

        lv_linear=(LinearLayout)findViewById(R.id.lv_linear);
        nottext=(TextView)findViewById(R.id.notext) ;
        recyclerview_excutive_list=(RecyclerView)findViewById(R.id.recyclerview_excutive_list);
        layoutManager= new LinearLayoutManager(this);
        recyclerview_excutive_list.setLayoutManager(layoutManager);
        search=(EditText)findViewById(R.id.search);
        executivelists= new ArrayList<>();
        search.addTextChangedListener(new TextWatcher() {
            String v_qty = search.getText().toString().trim();
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                isOnTextChanged = true;
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable arg0){
                // TODO Auto-generated method stub
//                if(isOnTextChanged){
//                    isOnTextChanged = false;
                String v_qty1 = search.getText().toString().trim();
//                    if(arg0.toString().trim().length()>0) {
                if(executivelists.size()>0){
                    adapter.getFilter().filter(arg0.toString().trim());
                }

                Log.d("mytag", "orderqty " + v_qty1 + "editable " + arg0.toString());
            }
        });

        if(CheckNetwork.isInternetAvailable(this)){
            Allexecutive();
        }else {
            Toast.makeText(MonthHistory.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }

    private void Allexecutive(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        executivelists = new ArrayList<>();
        myProgressDialog = MyProgressDialog.show(MonthHistory.this, "", "", true, false, null);
        Call<ResponseExecutive> call1 = apiInterface.excutive_list();
        call1.enqueue(new Callback<ResponseExecutive>() {
            @Override
            public void onResponse(Call<ResponseExecutive> call, retrofit2.Response<ResponseExecutive> response) {
                myProgressDialog.dismiss();
                if(response.isSuccessful()){
                    Log.d("mytag", "Response Date wise Daily +" + response.body().toString());
//
                    String rsp = String.valueOf(response.body().getCode());
                    executivelists = response.body().getExeList();

                    if (rsp.equals("1") == true) {
                        nottext.setVisibility(View.GONE);
                        lv_linear.setVisibility(View.VISIBLE);
                        recyclerview_excutive_list.setVisibility(View.VISIBLE);
                        adapter = new excutiveAdapter(MonthHistory.this, executivelists);
                        recyclerview_excutive_list.setAdapter(adapter);
                    }
                    if (rsp.equals("0")) {

                        lv_linear.setVisibility(View.GONE);
                        recyclerview_excutive_list.setVisibility(View.GONE);
                        nottext.setVisibility(View.VISIBLE);
                        nottext.setText("No Data Found");
                        // Toast.makeText(main_tab_activity.this, "Appointment not Available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseExecutive> call, Throwable t) {

                myProgressDialog.dismiss();
                lv_linear.setVisibility(View.GONE);
                nottext.setVisibility(View.VISIBLE);
                Toast.makeText(MonthHistory.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
//                call.cancel();
            }
        });

    }
}
