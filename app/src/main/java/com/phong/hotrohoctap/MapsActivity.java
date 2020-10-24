package com.phong.hotrohoctap;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static int feacke = 1;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onSearch(View view) {
        EditText location_tf = (EditText) findViewById(R.id.edtAddress);
        String location = location_tf.getText().toString();
        List<Address> addLayerList = null;
        if (!location.equals(" ")) {
            Geocoder geocoder = new Geocoder(MapsActivity.this);
            try {
                addLayerList = geocoder.getFromLocationName(location, feacke);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < addLayerList.size(); i++) {
                Address address = addLayerList.get(i);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(location_tf.getText().toString()));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(21.0381278, 105.7446413);
        mMap.addMarker(new MarkerOptions().position(latLng).title("FPT Polytechnich" + latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng , 16));
    }
}
