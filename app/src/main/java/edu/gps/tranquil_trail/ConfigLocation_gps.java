package edu.gps.tranquil_trail;

import static android.view.LayoutInflater.from;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class ConfigLocation_gps  extends Application {

    private static ConfigLocation_gps singleton;
    private List<Location>mylocation;
    private Location current;
    private   String[] locationNames;
    private   String[] locationDescription;

    public List<Location>getMylocation()
    {
        mylocation = get();
        return mylocation;
    }

    public Location Getmycurrentlocation()
    {
        return current;
    }

    public void Setmycurrentlocation(Location l)
    {
       current=l;
    }

    public void onCreate()
    {
        super.onCreate();
        locationNames =getResources().getStringArray(R.array.location_names);
        locationDescription = getResources().getStringArray(R.array.location_descriptions);
        singleton=this;
        mylocation=new ArrayList<>();
        current=new Location("");
    }

    public List<Location> get() {

        mylocation = new ArrayList<>();

        double[][] locations = {

                {32.011696931551064, 44.32392335946514},
                {36.19311159341868, 43.99831712029664},
                {30.96088566533865, 46.106096757585945},
                {32.61639774743967, 44.032525475360906},
                {33.35380443979348, 44.20203031065095},
                {32.542565028459094, 44.42279640461181},
                {36.772262101354194, 43.30466409207601},
                {36.73231475713401, 43.09500932896266},
                {36.49087248377457, 43.44243883958227},
                {36.731380036361564, 44.86165768191701},
                {36.12533356629912, 44.91913249626595},
                {33.09377575204545, 44.58057740995387},
                {32.010219778863764, 44.323466861765844},
                {32.028178962987724, 44.40078243945006},
                {32.03909808909404, 44.38031686407379},

        };

        locationNames =getResources().getStringArray(R.array.location_names);
        int i=0;
        for (double[] locationDetails : locations) {
            double latitude = locationDetails[0];
            double longitude = locationDetails[1];
            Location location = new Location(""+locationNames[i]);
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            mylocation.add(location);
            i++;
        }

        return mylocation;
    }



}







