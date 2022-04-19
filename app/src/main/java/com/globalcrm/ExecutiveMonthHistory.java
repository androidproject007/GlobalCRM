package com.globalcrm;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import com.globalcrm.Adapter.CurreuntMonthSalesAdapter;
import com.globalcrm.Adapter.excutiveAdapter;
import com.globalcrm.Model.ResponseAllTodayAppoitment;
import com.globalcrm.Model.ResponseTodaySale;
import com.globalcrm.Model.TodayApoList;
import com.globalcrm.Model.TodaySales;
import com.globalcrm.Other.CheckNetwork;
import com.globalcrm.Other.Datastorage;
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

public class ExecutiveMonthHistory extends AppCompatActivity {
    MyProgressDialog myProgressDialog;
    RecyclerView recyclerview_month_list;
    RecyclerView.LayoutManager layoutManager;
    boolean isOnTextChanged = false;
    List<TodaySales> current_month= new ArrayList<>();
    EditText search;
    TextView  nottext,ex_name;
    LinearLayout lv_linear;
    TextView month_total_sales;
    CurreuntMonthSalesAdapter adapter;
    Intent intent;
    String id,name;
    EditText date,date1;
    DatePickerDialog datePickerDialog,datePickerDialog1;
    SimpleDateFormat dateFormat,dateFormat1;
    TextView clear_search;
    TextView submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_ex_month_sales);

        lv_linear=(LinearLayout)findViewById(R.id.lv_linear);
        nottext=(TextView)findViewById(R.id.notext) ;
        ex_name=(TextView)findViewById(R.id.ex_name) ;
        month_total_sales=(TextView)findViewById(R.id.month_total_sales) ;
        recyclerview_month_list=(RecyclerView)findViewById(R.id.recyclerview_month_list);
        layoutManager= new LinearLayoutManager(this);
        recyclerview_month_list.setLayoutManager(layoutManager);
        search=(EditText)findViewById(R.id.search);
        current_month= new ArrayList<>();
        intent = getIntent();
        Bundle bundleintent = intent.getExtras();

        if(bundleintent != null){
            // paymentResponse = bundle.getString(TraknpayConstants.PAYMENT_RESPONSE);
            id = bundleintent.getString("id");
            name = bundleintent.getString("name");

            if(name!=null){
             ex_name.setText(name);
            }

            Log.d("mytag","id"+ id);
        }

        date=(EditText)findViewById(R.id.date);
        date1=(EditText)findViewById(R.id.date1);
        submit=(TextView)findViewById(R.id.submit);
        clear_search=(TextView)findViewById(R.id.clear_search);

        clear_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date.setText("");
                date1.setText("");
                if(id!=null){
                    if(CheckNetwork.isInternetAvailable(ExecutiveMonthHistory.this)){
                        Currentsales();
                    }else {
                        Toast.makeText(ExecutiveMonthHistory.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                final Calendar newDate = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(ExecutiveMonthHistory.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        newDate.set(year, monthOfYear, dayOfMonth);
                        date.setText(dateFormat.format(newDate.getTime()));
//                        if(!date.getText().toString().isEmpty()) {
//                            DateCurrentsales(date.getText().toString().trim());
//                        }else {
//                            Toast.makeText(ExecutiveMonthHistory.this, "Please Select Date", Toast.LENGTH_SHORT).show();
//                        }

                    }
                }, 2022, newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH));
                Calendar calendar = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

                date.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        // TODO Auto-generated method stub
                        datePickerDialog.show();
                    }
                });
            }
        });


        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                final Calendar newDate = Calendar.getInstance();
                datePickerDialog1 = new DatePickerDialog(ExecutiveMonthHistory.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date.getText().toString().isEmpty()) {
                    Toast.makeText(ExecutiveMonthHistory.this, "Please select Start date", Toast.LENGTH_SHORT).show();
                }else if(date1.getText().toString().isEmpty()) {
                    Toast.makeText(ExecutiveMonthHistory.this, "Please select End date", Toast.LENGTH_SHORT).show();
                }else {
                    DateCurrentsales(date.getText().toString().trim(),date1.getText().toString().trim());
                }
            }
        });


//
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
                if(current_month.size()>0) {
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

        if(id!=null) {
            if(CheckNetwork.isInternetAvailable(ExecutiveMonthHistory.this)){
                Currentsales();
            }else {
                Toast.makeText(ExecutiveMonthHistory.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }


        }
        //All_recyclerview_AppointmentList();

    }


    private void Currentsales(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        //  Log.d("mytag","date"+datevaluse);
        myProgressDialog = MyProgressDialog.show(ExecutiveMonthHistory.this, "", "", true, false, null);
        //String userid = (String) Datastorage.ReadFromPreference("user_id", Datastorage.STRING_KEY, main_tab_activity.this);
        Call<ResponseTodaySale> call1 = apiInterface.Monyhsales(id);
        call1.enqueue(new Callback<ResponseTodaySale>() {
            @Override
            public void onResponse(Call<ResponseTodaySale> call, retrofit2.Response<ResponseTodaySale> response) {
                myProgressDialog.dismiss();
                current_month= new ArrayList<>();
                Double Curreunt_total = 0.00;
                if(response.isSuccessful()) {
                    Log.d("mytag", "Response Date wise Daily +" + response.body().toString());
                    String rsp = String.valueOf(response.body().getCode());
                    if (rsp.equals("1") == true) {
                        current_month = response.body().getAppList();
                        if(current_month.size()!=0){
                            nottext.setVisibility(View.GONE);
                            recyclerview_month_list.setVisibility(View.VISIBLE);
                            lv_linear.setVisibility(View.VISIBLE);
                            adapter = new CurreuntMonthSalesAdapter(ExecutiveMonthHistory.this, current_month);
                            recyclerview_month_list.setAdapter(adapter);
                            month_total_sales.setText(String.valueOf(response.body().getTotalAmount()));

                        }else {
                            recyclerview_month_list.setVisibility(View.GONE);
                            lv_linear.setVisibility(View.VISIBLE);
                            nottext.setVisibility(View.VISIBLE);
                            nottext.setText("No Data Found");
                            month_total_sales.setText("0");
                        }
                    }
                    if (rsp.equals("0")) {
                        month_total_sales.setText("0");
                        recyclerview_month_list.setVisibility(View.GONE);
                        nottext.setVisibility(View.VISIBLE);
                        lv_linear.setVisibility(View.VISIBLE);
                        nottext.setText("No Data Found");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTodaySale> call, Throwable t) {
                myProgressDialog.dismiss();
                month_total_sales.setText("0");
                recyclerview_month_list.setVisibility(View.GONE);
                nottext.setVisibility(View.VISIBLE);
                lv_linear.setVisibility(View.VISIBLE);
                nottext.setText("No Data Found");
                Toast.makeText(ExecutiveMonthHistory.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
//                call.cancel();
            }
        });
    }

    private void DateCurrentsales(String date,String date1){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        myProgressDialog = MyProgressDialog.show(ExecutiveMonthHistory.this, "", "", true, false, null);
        Call<ResponseTodaySale> call1 = apiInterface.dateMonyhsales(id,date,date1);
        call1.enqueue(new Callback<ResponseTodaySale>() {
            @Override
            public void onResponse(Call<ResponseTodaySale> call, retrofit2.Response<ResponseTodaySale> response) {
                myProgressDialog.dismiss();
                current_month= new ArrayList<>();
                Double Curreunt_total = 0.00;
                if(response.isSuccessful()){
                    Log.d("mytag", "Response Date wise Daily +" + response.body().toString());
                    String rsp = String.valueOf(response.body().getCode());
                    if (rsp.equals("1") == true) {
                        current_month = response.body().getAppList();
                        if(current_month.size()!=0){
                            nottext.setVisibility(View.GONE);
                            recyclerview_month_list.setVisibility(View.VISIBLE);
                            lv_linear.setVisibility(View.VISIBLE);
                            adapter = new CurreuntMonthSalesAdapter(ExecutiveMonthHistory.this, current_month);
                            recyclerview_month_list.setAdapter(adapter);
                            month_total_sales.setText(String.valueOf(response.body().getTotalAmount()));
                        }else {
                            recyclerview_month_list.setVisibility(View.GONE);
                            lv_linear.setVisibility(View.VISIBLE);
                            nottext.setVisibility(View.VISIBLE);
                            nottext.setText("No Data Found");
                            month_total_sales.setText("0");
                        }
                    }
                    if (rsp.equals("0")) {
                        month_total_sales.setText("0");
                        recyclerview_month_list.setVisibility(View.GONE);
                        nottext.setVisibility(View.VISIBLE);
                        lv_linear.setVisibility(View.VISIBLE);
                        nottext.setText("No Data Found");

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTodaySale> call, Throwable t) {
                myProgressDialog.dismiss();
                month_total_sales.setText("0");
                recyclerview_month_list.setVisibility(View.GONE);
                nottext.setVisibility(View.VISIBLE);
                lv_linear.setVisibility(View.VISIBLE);
                nottext.setText("No Data Found");
                Toast.makeText(ExecutiveMonthHistory.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
//                call.cancel();
            }
        });
    }
}
