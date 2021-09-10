package com.geektech.android3hw8.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.geektech.android3hw8.data.model.Polyline;

@Dao
public interface PolylineDao {

    @Query("select * from polyline")
    Polyline getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Polyline polyline);
}
