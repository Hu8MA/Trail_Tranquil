package edu.gps.tranquil_trail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class about extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.aboutapp);




        // Set a listener for the bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.location:
                        Intent in = new Intent(about.this,Location_Display.class);

                        startActivity(in); // Replace with your first activity
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(about.this, Control.class)); // Replace with your second activity
                        return true;
                    case R.id.list:
                        startActivity(new Intent(about.this, listlocation.class)); // Replace with your third activity
                        return true;
                }
                return false;
            }
        });
    }
}