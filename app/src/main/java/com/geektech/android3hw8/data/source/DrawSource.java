package com.geektech.android3hw8.data.source;

import com.geektech.android3hw8.data.model.Polyline;

public interface DrawSource {

    Polyline getPolyline();

    void insert(String points, Integer id);
}
