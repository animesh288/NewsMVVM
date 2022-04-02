package com.android.newsmvvm.home.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.newsmvvm.R;

public class NewsHolder extends RecyclerView.ViewHolder {

    public TextView title,description,author;
    public CardView cardView;
    public ImageView imageView;

    public NewsHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.title);
        description=itemView.findViewById(R.id.description);
        author=itemView.findViewById(R.id.author);
        imageView=itemView.findViewById(R.id.image);
    }
}
