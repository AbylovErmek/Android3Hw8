package com.geektech.android3hw8.data.local;

import androidx.room.RoomDatabase;

import com.geektech.android3hw8.data.model.Polyline;

@androidx.room.Database(entities = {Polyline.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract PolylineDao polylineDao();
}
