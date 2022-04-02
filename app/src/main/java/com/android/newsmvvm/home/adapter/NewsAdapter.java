package com.android.newsmvvm.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.newsmvvm.R;
import com.android.newsmvvm.data.model.Article;
import com.android.newsmvvm.home.holder.NewsHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

    private List<Article> articleList;
    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_item,null,false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.title.setText(articleList.get(position).getTitle());
        holder.description.setText(articleList.get(position).getDescription());
        holder.author.setText(articleList.get(position).getAuthor());
        Picasso.get().load(articleList.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(articleList==null) return 0;
        return articleList.size();
    }
}
