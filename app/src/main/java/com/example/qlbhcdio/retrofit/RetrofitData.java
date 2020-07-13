package com.example.qlbhcdio.retrofit;




import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitData {

    public  static Retrofit mRetrofit;

    public  static Retrofit getClient(String baseURL){

        OkHttpClient builder = new OkHttpClient.Builder()
                                        .readTimeout(10, TimeUnit.SECONDS)
                                        .writeTimeout(10,TimeUnit.SECONDS)
                                        .connectTimeout(30,TimeUnit.SECONDS)
                                        .retryOnConnectionFailure(true)
                                        .build();
        Gson gson = new GsonBuilder().setLenient().create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        return mRetrofit;
    }
}
