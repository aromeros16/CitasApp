package com.example.citasapp.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.citasapp.R;

public class SelectInformationActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_information);

        imageView = findViewById(R.id.imageAboutUsSingle);
        textView = findViewById(R.id.textAboutUsSingle);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String image = intent.getStringExtra("image");
            String description = intent.getStringExtra("description");

            if (!name.equals("null")){
                description = name + "\n" + description;
            }
            textView.setText(description);

            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
        }
    }


}
