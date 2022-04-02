package com.android.newsmvvm.home.viewmodel;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.newsmvvm.data.dao.NewsDao;
import com.android.newsmvvm.data.database.NewsDatabase;
import com.android.newsmvvm.data.model.Article;
import com.android.newsmvvm.data.model.NewsResponse;
import com.android.newsmvvm.network.NewsServiceApi;
import com.android.newsmvvm.network.RetrofitFactory;
import com.android.newsmvvm.util.NetworkUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    private final String apiKey="612dfad3379e4ed9b82bf6e1746ac0ca";
    private MutableLiveData<List<Article>> liveData;
    NewsDao newsDao;
    NewsServiceApi newsServiceApi;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        liveData=new MutableLiveData<>();
    }

    public MutableLiveData<List<Article>> getNewsData(Context context){
        if(newsServiceApi==null){
            newsServiceApi= RetrofitFactory.getRetrofitInstance().create(NewsServiceApi.class);
        }
        createDatabaseInstance(context);
        createCall(context);
        return liveData;

    }

    private void createDatabaseInstance(Context context) {
        newsDao= NewsDatabase.getDatabaseInstance(context).newsDao();
    }

    private void createCall(Context context) {
        if(NetworkUtil.isNetworkConnected(context)) {
            Call<NewsResponse> call = newsServiceApi.getNews("in", 99, apiKey);
            call.enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            liveData.postValue(response.body().getArticles());
                        addData(response.body().getArticles());
                        }
                    } else {
                        Toast.makeText(context, "response failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {
                    Toast.makeText(context, "call failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            getPreviousNews();
        }
    }

    private void addData(List<Article> articles) {
        if(articles!=null && !articles.isEmpty()){
            for(Article article:articles){
                addDataInBg(article);
            }
        }
    }

    private void addDataInBg(Article article) {

        class AsyncDataSaver extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                newsDao.addArticle(article);
                return null;
            }
        }
        AsyncDataSaver asyncDataSaver = new AsyncDataSaver();
        asyncDataSaver.execute();
    }

    private void getPreviousNews() {
        class GetData extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                liveData.postValue(newsDao.getArticles());
                return null;
            }
        }
        GetData getData=new GetData();
        getData.execute();
    }
}
