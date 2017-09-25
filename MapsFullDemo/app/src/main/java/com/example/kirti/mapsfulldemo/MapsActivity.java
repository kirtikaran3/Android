package com.example.kirti.mapsfulldemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.i("Status ","In requestPermResult");
            if(requestCode==1){

                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){

                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        updateMap(lastKnownLocation);
                        Log.i("Status ","Check Permission and request");
                    }
                }

            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void updateMap(Location location){

        Log.i("Status ","In Update Map");
        LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(userLocation).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,12));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

         locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
          locationListener = new LocationListener() {
              @Override
              public void onLocationChanged(Location location) {

                  Log.i("Status","On location changed");
                  updateMap(location);

                  String myAddress="";

                  Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                  try {
                      List<Address> listAddress =  geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                      if(listAddress !=null && listAddress.size()>0){

                          Log.i("Place Info:",listAddress.get(0).toString());

                          if(listAddress.get(0).getAddressLine(0)!=null){

                              myAddress += listAddress.get(0).getAddressLine(0)+",";
                              myAddress += listAddress.get(0).getAddressLine(1)+",";
                              myAddress += listAddress.get(0).getAddressLine(2)+", ";
                              myAddress += listAddress.get(0).getAddressLine(3);

                          }

                          Toast.makeText(getApplicationContext(),myAddress,Toast.LENGTH_LONG).show();


                      }

                  } catch (IOException e) {
                      e.printStackTrace();
                  }


              }

              @Override
              public void onStatusChanged(String s, int i, Bundle bundle) {

              }

              @Override
              public void onProviderEnabled(String s) {

              }

              @Override
              public void onProviderDisabled(String s) {

              }
          };

          if(Build.VERSION.SDK_INT<23){

              locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Log.i("Status","Build Version check");
          }
          else{

              if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

                  ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
              }
              else{

                  Log.i("Status ","In lastKnownLocation");

                  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                  Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                  updateMap(lastKnownLocation);

              }

          }


    }
}
