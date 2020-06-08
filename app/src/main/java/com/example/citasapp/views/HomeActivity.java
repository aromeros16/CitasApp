package com.example.citasapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.citasapp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_singOut:
                logOut();
                break;
        }

        drawe.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
        onBackPressed();
    }
}
