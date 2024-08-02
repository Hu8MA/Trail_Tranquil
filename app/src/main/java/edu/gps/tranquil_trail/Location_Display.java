package edu.gps.tranquil_trail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.location.Location;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.List;


public class Location_Display extends AppCompatActivity {
    public static final int Permission_FINE_LOCATION=88;
    private BottomNavigationView bottomNavigationView;

    MapView mapView;
    ConfigLocation_gps myApplication;
    public List<Location> savedlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        // Initialize the osmdroid configuration
        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE));
        mapView = findViewById(R.id.mapView);
        myApplication=(ConfigLocation_gps)getApplicationContext();
        savedlocation = myApplication.getMylocation();
        setupMap();
        setmylocation(myApplication.Getmycurrentlocation());
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.location);
        // Set a listener for the bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.list:
                        startActivity(new Intent(Location_Display.this, listlocation.class)); // Replace with your first activity
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(Location_Display.this, Control.class)); // Replace with your second activity
                        return true;
                    case R.id.aboutapp:
                        startActivity(new Intent(Location_Display.this, about.class)); // Replace with your third activity
                        return true;
                }
                return false;
            }
        });
    }
    public void setmylocation(Location current)
    {
        double n,s;
        n = current.getLatitude();
        s = current.getLongitude();
        BoundingBox boundingBox = new BoundingBox( n, s, n, s );
        current.setProvider("Me");
        addMarker(current,true);

        mapView.zoomToBoundingBox(boundingBox, true);
    }

    private void setupMap() {
        // Enable the ability to zoom with gestures
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(15); // Adjust the zoom level as needed
        mapView.setMinZoomLevel(4.0);
        mapView.setMaxZoomLevel(18.0);

        // Initialize the map controller
        IMapController mapController = mapView.getController();

        // Check if there are saved locations
        if (savedlocation != null && !savedlocation.isEmpty()) {

            for (Location location : savedlocation) {
                addMarker(location,false);
            }

        }


    }

    private void addMarker(Location location,boolean flag) {
        if (mapView != null) {
            // Create a marker with a custom icon (you can customize this part)
            // Use the osmdroid Marker class
            org.osmdroid.views.overlay.Marker marker = new org.osmdroid.views.overlay.Marker(mapView);


            marker.setPosition(new GeoPoint(location.getLatitude(), location.getLongitude()));
            marker.setTitle(location.getProvider());

            if(flag)
            {
                marker.setIcon(getResources().getDrawable(R.drawable.location));
            }
            else
            {
                marker.setIcon(getResources().getDrawable(R.drawable.locations));

            }

             mapView.getOverlays().add(marker);
        }
    }


    }
