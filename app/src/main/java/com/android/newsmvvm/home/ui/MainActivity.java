package com.android.newsmvvm.home.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.newsmvvm.R;
import com.android.newsmvvm.data.model.Article;
import com.android.newsmvvm.home.adapter.NewsAdapter;
import com.android.newsmvvm.home.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel viewModel;
    RecyclerView recyclerView;
    List<Article> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        initView();
        getNews();
    }
    private void initViewModel() {
        viewModel= new ViewModelProvider(this).get(MainActivityViewModel.class);
    }
    private void initView() {
        recyclerView=findViewById(R.id.recyclerView);
    }
    private void getNews() {
        viewModel.getNewsData(this).observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                if(articles!=null){
                    list=articles;
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    NewsAdapter newsAdapter=new NewsAdapter();
                    newsAdapter.setContext(MainActivity.this);
                    newsAdapter.setArticleList(list);
                    recyclerView.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();
                }
            }
        });
    }




}