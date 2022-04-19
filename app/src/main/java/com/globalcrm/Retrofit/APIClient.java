package com.globalcrm.Retrofit;





import com.globalcrm.Other.Utills;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;
    private static Retrofit retrofittwo = null;
    private static Retrofit retrofitthree = null;
    private static Retrofit retrofitfour = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Utills.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static Retrofit getClientTwo(){
        // customize client for second Retrofit instance for API v2
        HttpLoggingInterceptor interceptor2 = new HttpLoggingInterceptor();
        interceptor2.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient2 = new OkHttpClient().newBuilder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .addInterceptor(interceptor2)
                .build();

        retrofittwo = new Retrofit.Builder()
                .baseUrl(Utills.BaseUrlTwo)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient2)
                .build();

        return  retrofittwo;
    }


 public static Retrofit getClientThree(){
        // customize client for second Retrofit instance for API v2
        HttpLoggingInterceptor interceptor2 = new HttpLoggingInterceptor();
        interceptor2.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient2 = new OkHttpClient().newBuilder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .addInterceptor(interceptor2)
                .build();

        retrofitthree = new Retrofit.Builder()
                .baseUrl(Utills.BaseUrlThree)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient2)
                .build();

        return  retrofitthree;
    }

    public static Retrofit getClientFour(){
        // customize client for second Retrofit instance for API v2
        HttpLoggingInterceptor interceptor2 = new HttpLoggingInterceptor();
        interceptor2.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient2 = new OkHttpClient().newBuilder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .addInterceptor(interceptor2)
                .build();

        retrofitfour = new Retrofit.Builder()
                .baseUrl(Utills.BaseUrlFour)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient2)
                .build();

        return  retrofitfour;
    }
}
