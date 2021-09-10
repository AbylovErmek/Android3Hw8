package com.geektech.android3hw8.data.repository;

import com.geektech.android3hw8.data.model.Polyline;
import com.geektech.android3hw8.data.source.DrawSource;

public class DrawRepository {

    private final DrawSource source;

    public DrawRepository(DrawSource source) {
        this.source = source;
    }

    public Polyline getPolyline() {
        return source.getPolyline();
    }

    public void insert(String points, Integer id) {
        source.insert(points, id);
    }
}
