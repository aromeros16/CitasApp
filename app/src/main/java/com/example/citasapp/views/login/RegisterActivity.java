package com.example.citasapp.views.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.citasapp.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Ocultar actionbar
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
