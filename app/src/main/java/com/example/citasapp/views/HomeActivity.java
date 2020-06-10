package com.example.citasapp.views;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.citasapp.R;
import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawe;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Hooks
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.ltNav);
        drawe = findViewById(R.id.drawer_layout);

        //Tool bar
        setSupportActionBar(toolbar);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawe,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawe.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavController navController = Navigation.findNavController(this,R.id.fragmentNavHost);
        NavigationUI.setupWithNavController(navigationView,navController);

    }

    @Override
    public void onBackPressed() {

        if(drawe.isDrawerOpen(GravityCompat.START)){
            drawe.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}
