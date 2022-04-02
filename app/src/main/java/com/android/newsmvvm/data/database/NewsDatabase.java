package com.android.newsmvvm.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.newsmvvm.data.dao.NewsDao;
import com.android.newsmvvm.data.model.Article;

@Database(entities = {Article.class},
        version = 1,
        exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="NewsDatabase";

    private static volatile NewsDatabase INSTANCE;

    public abstract NewsDao newsDao();

    public static NewsDatabase getDatabaseInstance(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context,NewsDatabase.class,DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }

}
