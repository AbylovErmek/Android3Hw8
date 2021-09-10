package com.geektech.android3hw8.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.geektech.android3hw8.App;
import com.geektech.android3hw8.R;
import com.geektech.android3hw8.databinding.ActivityMainBinding;
import com.geektech.android3hw8.utils.ListConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap map;
    private ActivityMainBinding binding;
    private ActivityResultLauncher<String[]> resultLauncher;
    private List<LatLng> points = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkPermissions();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        setOnClicks();

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(this);
        checkDataFromRepo();
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions()
                .draggable(true)
                .position(latLng);
        map.addMarker(markerOptions);
        points.add(latLng);
    }

    private void checkDataFromRepo() {
        if (App.repository.getPolyline().getPoints() != null) {
            this.points = ListConverter.toLatlangList(App.repository.getPolyline().getPoints());
            drawPolyline(points);
        }
    }

    private void drawPolyline(List<LatLng> points) {
        PolylineOptions polylineOptions = new PolylineOptions()
                .width(15f)
                .addAll(points);
        map.addPolyline(polylineOptions);
    }

    private void checkPermissions() {
        resultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            for (Map.Entry<String, Boolean> entry : result.entrySet()) {
                if (!entry.getValue()) launchRequest();
            }
        });
        launchRequest();
    }

    private void launchRequest() {
        resultLauncher.launch(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION});
    }


    private void setOnClicks() {
        binding.btnMapDraw.setOnClickListener(v -> drawPolyline(points));
        binding.clearMapBtn.setOnClickListener(v -> {
            map.clear();
            points.removeAll(Collections.unmodifiableList(points));
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (points != null) {
            App.repository.insert(ListConverter.fromLatlangList(points), 1);
            Log.d("TAG", "onPause: " + ListConverter.fromLatlangList(points));
        }
    }
}