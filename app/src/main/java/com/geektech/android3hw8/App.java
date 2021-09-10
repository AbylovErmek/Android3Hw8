package com.geektech.android3hw8;

import android.app.Application;

import androidx.room.Room;

import com.geektech.android3hw8.data.local.Database;
import com.geektech.android3hw8.data.local.RoomSource;
import com.geektech.android3hw8.data.repository.DrawRepository;
import com.geektech.android3hw8.data.source.DrawSource;

public class App extends Application {

    public static Database database;
    public static DrawRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        createDatabase();
        setSource();
    }

    private void setSource() {
        RoomSource roomSource = new RoomSource();
        repository = new DrawRepository(roomSource);
    }

    private void createDatabase() {
        database = Room.databaseBuilder(this, Database.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
