package com.example.zeger.apps_si3004.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zeger.apps_si3004.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by zeger on 22/04/17.
 */

public class MapsFragment extends Fragment implements OnMapReadyCallback{
    private GoogleMap googleMap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps,container,false);

        MapView mapView = (MapView) view.findViewById(R.id.map);

        mapView.onCreate(savedInstanceState); // override maps on create
        mapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(this);

        return view;
    }

    public void updateLocation(Location location){
        if(googleMap!=null) {
            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_24dp);

            Marker myPos = googleMap.addMarker(new MarkerOptions()
                    .position(latLng).icon(icon)
            );

            CameraPosition camPos = new CameraPosition.Builder()
                    .target(latLng)      // Sets the center of the map to LatLng (refer to previous snippet)
                    .zoom(14)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .build();

            CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(camPos);

            //googleMap.moveCamera(camUpdate);

            googleMap.animateCamera(camUpdate);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

            LocationManager locationManager = (LocationManager)
                    getActivity().getSystemService(Context.LOCATION_SERVICE);

            // Create a criteria object to retrieve provider
            Criteria criteria = new Criteria();
            //criteria.setAccuracy(50);

            // Get the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            Location location = locationManager.getLastKnownLocation(provider);

            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            try {
                if(location!=null) {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                    Log.d("LOCATION", addresses.get(0).getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(location!=null) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_24dp);

                Marker myPos = googleMap.addMarker(new MarkerOptions()
                        .position(latLng).icon(icon)
                );

                CameraPosition camPos = new CameraPosition.Builder()
                        .target(latLng)      // Sets the center of the map to LatLng (refer to previous snippet)
                        .zoom(14)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .build();

                CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(camPos);

                //googleMap.moveCamera(camUpdate);

                googleMap.animateCamera(camUpdate);
                //googleMap.setMyLocationEnabled(true);

                //settingMyLocation(googleMap);
            }

        }
    }
}
