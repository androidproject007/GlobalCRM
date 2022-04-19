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
import com.globalcrm.Adapter.FollowupAdapter;
import com.globalcrm.Adapter.TodayAppoitmentAdapter;
import com.globalcrm.Model.FollowupList;
import com.globalcrm.Model.FollowupResonse;
import com.globalcrm.Model.ResponseAllTodayAppoitment;
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

public class FollowupHistory extends AppCompatActivity {
    MyProgressDialog myProgressDialog;
    RecyclerView recyclerview_today_sales;
    RecyclerView.LayoutManager layoutManager;
    boolean isOnTextChanged = false;
    List<FollowupList>followupApoList= new ArrayList<>();
    EditText search;
    TextView  nottext,today_total_sales;
    LinearLayout lv_linear;
    FollowupAdapter adapter;
    TextView clear_search;
    EditText date,date1;
    DatePickerDialog datePickerDialog,datePickerDialog1;
    SimpleDateFormat dateFormat,dateFormat1;
    TextView submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followup);



        date=(EditText)findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                final Calendar newDate = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(FollowupHistory.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        newDate.set(year, monthOfYear, dayOfMonth);
                        date.setText(dateFormat.format(newDate.getTime()));
//                        if(!date.getText().toString().isEmpty()) {
//                            Datehistory(date.getText().toString().trim());
//                        }else {
//                            Toast.makeText(FollowupHistory.this, "Please Select Date", Toast.LENGTH_SHORT).show();
//                        }

                    }
                }, 2022, newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH));
                Calendar calendar = Calendar.getInstance();
                //datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        datePickerDialog.show();
                    }
                });
            }
        });

        date1=(EditText)findViewById(R.id.date1);

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                final Calendar newDate = Calendar.getInstance();
                datePickerDialog1 = new DatePickerDialog(FollowupHistory.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        newDate.set(year, monthOfYear, dayOfMonth);
                        date1.setText(dateFormat1.format(newDate.getTime()));

//                        if(!date1.getText().toString().isEmpty()) {
//                            Datehistory(date1.getText().toString().trim());
//                        }else {
//                            Toast.makeText(TodayHistory.this, "Please Select Date", Toast.LENGTH_SHORT).show();
//                        }


                    }
                }, 2022, newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH));
                Calendar calendar = Calendar.getInstance();
                datePickerDialog1.getDatePicker().setMaxDate(calendar.getTimeInMillis());

                date1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        datePickerDialog1.show();
                    }
                });
            }
        });

        clear_search=(TextView)findViewById(R.id.clear_search);
        clear_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date.setText("");
                date1.setText("");
                if(CheckNetwork.isInternetAvailable(FollowupHistory.this)){
                    All_recyclerview_followup();
                }else {
                    Toast.makeText(FollowupHistory.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        });

        submit=(TextView)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date.getText().toString().isEmpty()) {
                    Toast.makeText(FollowupHistory.this, "Please select Start date", Toast.LENGTH_SHORT).show();
                }else if(date1.getText().toString().isEmpty()) {
                    Toast.makeText(FollowupHistory.this, "Please select End date", Toast.LENGTH_SHORT).show();
                }else {
                    //DateCurrentsales(date.getText().toString().trim(),date1.getText().toString().trim());
                    Datehistory(date.getText().toString().trim(),date1.getText().toString().trim());
                }
            }
        });

        lv_linear=(LinearLayout)findViewById(R.id.lv_linear);
        nottext=(TextView)findViewById(R.id.notext) ;
        today_total_sales=(TextView)findViewById(R.id.today_total_sales) ;
        recyclerview_today_sales=(RecyclerView)findViewById(R.id.recyclerview_today_sales);
        layoutManager= new LinearLayoutManager(this);
        recyclerview_today_sales.setLayoutManager(layoutManager);

        search=(EditText)findViewById(R.id.search);
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
                if(followupApoList.size()>0) {
                    adapter.getFilter().filter(arg0.toString().trim());
                }
//                        fetche_Search_list(arg0.toString().trim());
//                    objects.get(position).setQty(arg0.toString().trim());
                Log.d("mytag", "orderqty " + v_qty1 + "editable " + arg0.toString());
                // }
//                    }else {
//                        ClientList();
//                    }
//                    notifyItemChanged(position,objects.size());
                // }
            }
        });

        if(CheckNetwork.isInternetAvailable(FollowupHistory.this)){
            All_recyclerview_followup();
        }else {
            Toast.makeText(FollowupHistory.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }

    private void All_recyclerview_followup(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        followupApoList = new ArrayList<>();
        myProgressDialog = MyProgressDialog.show(FollowupHistory.this, "", "", true, false, null);
        Call<FollowupResonse> call1 = apiInterface.followuplist();
        call1.enqueue(new Callback<FollowupResonse>(){
            @Override
            public void onResponse(Call<FollowupResonse> call, retrofit2.Response<FollowupResonse> response){
                myProgressDialog.dismiss();
                if(response.isSuccessful()){
                    Log.d("mytag", "Response Date wise Daily +" + response.body().toString());
                    String rsp = String.valueOf(response.body().getCode());
                    followupApoList = response.body().getAppList();
                    //String rsp = obj.getString("code");
                    if(rsp.equals("1") == true){
                        nottext.setVisibility(View.GONE);
                        lv_linear.setVisibility(View.VISIBLE);
                        recyclerview_today_sales.setVisibility(View.VISIBLE);
                        adapter = new FollowupAdapter(FollowupHistory.this, followupApoList);
                        recyclerview_today_sales.setAdapter(adapter);
                    }
                    if(rsp.equals("0")){

                        lv_linear.setVisibility(View.VISIBLE);
                        recyclerview_today_sales.setVisibility(View.GONE);
                        nottext.setVisibility(View.VISIBLE);
                        nottext.setText("No Data Found");

                        // Toast.makeText(main_tab_activity.this, "Appointment not Available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<FollowupResonse> call, Throwable t) {
                Log.d("mytag",""+t.getMessage());
                myProgressDialog.dismiss();

                lv_linear.setVisibility(View.GONE);
                nottext.setVisibility(View.VISIBLE);
               Toast.makeText(FollowupHistory.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
//                call.cancel();
            }
        });

    }

    private void Datehistory(String date,String date1) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        //  Log.d("mytag","date"+datevaluse);
        followupApoList = new ArrayList<>();
        myProgressDialog = MyProgressDialog.show(FollowupHistory.this, "", "", true, false, null);
        // String userid = (String) Datastorage.ReadFromPreference("user_id", Datastorage.STRING_KEY, TodayHistory.this);
        Call<FollowupResonse> call1 = apiInterface.FollowupDate(date,date1);
        call1.enqueue(new Callback<FollowupResonse>() {
            @Override
            public void onResponse(Call<FollowupResonse> call, retrofit2.Response<FollowupResonse> response){
                myProgressDialog.dismiss();
                if(response.isSuccessful()){
                    Log.d("mytag", "Response Date wise Daily +" + response.body().toString());
//                JSONObject obj = new JSONObject(response);
                    String rsp = String.valueOf(response.body().getCode());
                    followupApoList = response.body().getAppList();
                    //String rsp = obj.getString("code");
                    if (rsp.equals("1") == true) {
                        nottext.setVisibility(View.GONE);
                        lv_linear.setVisibility(View.VISIBLE);
                        recyclerview_today_sales.setVisibility(View.VISIBLE);
                        adapter = new FollowupAdapter(FollowupHistory.this, followupApoList);
                        recyclerview_today_sales.setAdapter(adapter);

                    }

                    if (rsp.equals("0")) {
                        lv_linear.setVisibility(View.VISIBLE);
                        recyclerview_today_sales.setVisibility(View.GONE);
                        nottext.setVisibility(View.VISIBLE);
                        nottext.setText("No Data Found");
                        today_total_sales.setText("0");
                        // Toast.makeText(main_tab_activity.this, "Appointment not Available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<FollowupResonse> call, Throwable t) {
                myProgressDialog.dismiss();

                lv_linear.setVisibility(View.GONE);
                nottext.setVisibility(View.VISIBLE);
                today_total_sales.setText("0");
                Toast.makeText(FollowupHistory.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
//                call.cancel();
            }
        });

    }
}
