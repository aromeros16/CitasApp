package com.example.citasapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.citasapp.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    //Variables
    private Animation topAnim, bottonAnim;
    private ImageView imageView;
    private TextView txtApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Ocultar actionbar
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottonAnim = AnimationUtils.loadAnimation(this,R.anim.botton_animation);

        //Hooks
        imageView = findViewById(R.id.imageSplash);
        txtApp = findViewById(R.id.txtCitaApp);

        imageView.setAnimation(topAnim);
        txtApp.setAnimation(bottonAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent (SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
