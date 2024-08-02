package edu.gps.tranquil_trail;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
public class Control extends AppCompatActivity {
    TextView tv_sensor , tv_updates , tv_address;
    Switch sw_locationupdates,sw_gps;
     FusedLocationProviderClient fusedLocationProviderClient;
     LocationCallback locationCallBack;
     LocationRequest locationRequest;
    public static final int Permission_FINE_LOCATION=88;
    public static final int Defult_update_interval = 30;
    public static final int Fast_update_interval = 5;
    private BottomNavigationView bottomNavigationView;
    private ConfigLocation_gps myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_control);

        tv_sensor =findViewById(R.id.tv_sensor);
        tv_updates =findViewById(R.id.tv_updates);
        tv_address=findViewById(R.id.tv_address);
        sw_gps=findViewById(R.id.sw_gps);
        sw_locationupdates=findViewById(R.id.sw_locationsupdates);


        //set all properties of locationRequest
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000*Defult_update_interval);
        locationRequest.setFastestInterval(1000*Fast_update_interval);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        myApplication=(ConfigLocation_gps)getApplicationContext();

        locationCallBack = new LocationCallback()
        {
            @Override
            public void onLocationResult(LocationResult locationResult)
            {
                super.onLocationResult(locationResult);

                updateUI(locationResult.getLastLocation());
            }
        };

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sw_gps.isChecked()){
                    //in mean use GPS
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Using GPS sensor");
                }
                else
                {
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("Using Towers + WiFi");
                }
            }
        });
        sw_locationupdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_locationupdates.isChecked())
                {
                    StartLocationUpdate();
                }
                else
                {
                    StopLocationUpdate();

                }
            }
        });
        updateGPS();
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.setting);

        // Set a listener for the bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.location:
                            startActivity(new Intent(Control.this, Location_Display.class)); // Replace with your first activity
                        return true;
                    case R.id.aboutapp:
                        startActivity(new Intent(Control.this, about.class)); // Replace with your second activity
                        return true;
                    case R.id.list:
                        startActivity(new Intent(Control.this, listlocation.class)); // Replace with your third activity
                        return true;
                }
                return false;
            }
        });

    }


    @SuppressLint("MissingPermission")
    public  void StartLocationUpdate()
    {
        tv_updates.setText("location is being tracked");
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallBack,null);
        updateGPS();
    }

    public  void StopLocationUpdate()
    {
        tv_updates.setText("location is stop tracking");

        tv_address.setText("not tracking location");

        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);

    }
    // is use for make request for user to allow access for your location by make  Permissions for this option
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode)
        {
            case Permission_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    updateGPS();
                }
                else
                {
                    Toast.makeText(this, "this app need the permission to be get current location", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    private void updateGPS()
    {
        //get permission from user to track GPS
        //get the current location from fused client
        //update the UI client

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Control.this);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            // user provided permission
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    //update all UI component
                    myApplication.Setmycurrentlocation(location);
                    updateUI(location);
                }
            });
        }
        else
        {
            //permissions not granted yet.
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Permission_FINE_LOCATION);
            }
        }
    }
    private  void updateUI(Location location) {

        Geocoder geocoder = new Geocoder(Control.this);
        try
        {
            List<Address> addressList =geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            tv_address.setText(    addressList.get(0).getAddressLine(0)   );
        }
        catch(Exception e)
        {
            tv_address.setText("Unable to get location address");
        }
    }
}