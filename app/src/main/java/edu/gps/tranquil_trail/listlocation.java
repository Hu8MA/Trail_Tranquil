package edu.gps.tranquil_trail;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RemoteViews;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
public class listlocation extends AppCompatActivity {
    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<Models> models = new ArrayList<>();
    ArrayList<Models> searchlist ;
    private BottomNavigationView bottomNavigationView;
    String[] locationNames =new String[16];
    String[] locationDescription =new String[16];
    int [] image =new int [16];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listlocation);
        locationNames =getResources().getStringArray(R.array.location_names);
        locationDescription = getResources().getStringArray(R.array.location_descriptions);
        //////////////
        TypedArray imgResources = getResources().obtainTypedArray(R.array.imgs);
        int length = imgResources.length();
        int[] resourceIds = new int[length];

        for (int i = 0; i < length; i++) {
            resourceIds[i] = imgResources.getResourceId(i, 0);
        }
        imgResources.recycle();
        // Now, resourceIds[] contains the array of drawable resource IDs
        searchView = findViewById(R.id.Search);
        searchView.clearFocus();
        recyclerView = findViewById(R.id.recyclerView);

        for(int j=0 ; j<locationNames.length;j++)
        {
            Models model = new Models();
            model.setName( locationNames[j]);
            model.setDescription( locationDescription[j]);
            model.setImg( resourceIds[j]);
            models.add(model);

        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(listlocation.this);
        recyclerView.setLayoutManager(layoutManager);

        ModelsAdeptor modeladeptor = new ModelsAdeptor(listlocation.this,models);
        recyclerView.setAdapter(modeladeptor);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchlist= new ArrayList<>();

                if(query.length()>0)
                {

                    for (int k=0 ; k<models.size() ;k++)
                    {

                        if(models.get(k).getName().toUpperCase().contains(query.toUpperCase()))
                        {

                            Models simplemodel = new Models();

                            simplemodel.setName(models.get(k).getName());
                            simplemodel.setDescription(models.get(k).getDescription());
                            simplemodel.setImg(models.get(k).getImg());
                            searchlist.add(simplemodel);

                        }
                    }


                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(listlocation.this);
                    recyclerView.setLayoutManager(layoutManager);

                    ModelsAdeptor model = new ModelsAdeptor(listlocation.this,searchlist);
                    recyclerView.setAdapter(model);
                }
                else
                {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(listlocation.this);
                    recyclerView.setLayoutManager(layoutManager);
                    ModelsAdeptor model = new ModelsAdeptor(listlocation.this,models);
                    recyclerView.setAdapter(model);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchlist= new ArrayList<>();

                if(newText.length()>0)
                {

                    for (int k=0 ; k<models.size() ;k++)
                    {

                        if(models.get(k).getName().toUpperCase().contains(newText.toUpperCase()))
                        {

                            Models simplemodel = new Models();

                            simplemodel.setName(models.get(k).getName());
                            simplemodel.setDescription(models.get(k).getDescription());
                            simplemodel.setImg(models.get(k).getImg());

                            searchlist.add(simplemodel);

                        }
                    }


                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(listlocation.this);
                    recyclerView.setLayoutManager(layoutManager);
                    ModelsAdeptor model = new ModelsAdeptor(listlocation.this,searchlist);
                    recyclerView.setAdapter(model);
                }
                else
                {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(listlocation.this);
                    recyclerView.setLayoutManager(layoutManager);
                    ModelsAdeptor model = new ModelsAdeptor(listlocation.this,models);
                    recyclerView.setAdapter(model);
                }
                return false;
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.list);

        // Set a listener for the bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.location:
                        startActivity(new Intent(listlocation.this, Location_Display.class)); // Replace with your first activity
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(listlocation.this, Control.class)); // Replace with your second activity
                        return true;
                    case R.id.aboutapp:
                        startActivity(new Intent(listlocation.this, about.class)); // Replace with your third activity
                        return true;
                }
                return false;
            }
        });
    }
}