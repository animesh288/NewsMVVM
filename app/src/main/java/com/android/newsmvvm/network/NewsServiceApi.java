package com.android.newsmvvm.network;

import com.android.newsmvvm.data.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsServiceApi {

    @GET("top-headlines")
    Call<NewsResponse> getNews(@Query("country") String country,
                               @Query("pageSize") int pageSize,
                               @Query("apiKey") String apiKey
    );
}
