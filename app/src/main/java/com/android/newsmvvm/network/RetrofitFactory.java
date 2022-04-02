package com.android.newsmvvm.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static Retrofit retrofitInstance;
    private static final String BASEURL="https://newsapi.org/v2/";

    public static Retrofit getRetrofitInstance(){
        if(retrofitInstance==null){
            retrofitInstance=new Retrofit.Builder()
                    .baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }
}
