package com.geektech.android3hw8.data.local;

import com.geektech.android3hw8.App;
import com.geektech.android3hw8.data.model.Polyline;
import com.geektech.android3hw8.data.source.DrawSource;

public class RoomSource implements DrawSource {

    @Override
    public Polyline getPolyline() {
        Polyline polyline = App.database.polylineDao().getAll();
        if (polyline == null)
            return new Polyline();
        return polyline;
    }

    @Override
    public void insert(String points, Integer id) {
        App.database.polylineDao().insert(new Polyline(id, points));
    }
}
