package com.example.citasapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.citasapp.R;
import com.google.firebase.auth.FirebaseAuth;

enum ProviderType {
    BASIC,
    GOOGLE
}

public class HomeActivity extends AppCompatActivity {

    Button signOut;
    TextView email,provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email = findViewById(R.id.textEmail_home);
        provider = findViewById(R.id.textProvider_home);
        signOut = findViewById(R.id.buttonSignOut);
        //Setup
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String provider = intent.getStringExtra("provider");
        setup(email,provider);

        // Saved data
        SharedPreferences prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("email",email);
        edit.putString("provider",provider);
        edit.apply();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

    }

    private void logOut() {
        SharedPreferences prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.clear();
        edit.apply();

        FirebaseAuth.getInstance().signOut();
        onBackPressed();
    }

    private void setup(String txtemail, String txtprovider){
        email.setText(txtemail);
        provider.setText(txtprovider);
    }
}
