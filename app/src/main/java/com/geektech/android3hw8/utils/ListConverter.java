package com.geektech.android3hw8.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ListConverter {

    public static String fromLatlangList(List<LatLng> points) {
        if (points == null)
            return (null);
        return new Gson().toJson(points, new TypeToken<List<LatLng>>() {
        }.getType());
    }

    public static List<LatLng> toLatlangList(String points) {
        if (points == null)
            return (null);
        return new Gson().fromJson(points, new TypeToken<List<LatLng>>() {
        }.getType());
    }
}
