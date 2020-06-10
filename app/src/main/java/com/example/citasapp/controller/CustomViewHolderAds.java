package com.example.citasapp.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;

public class CustomViewHolderAds extends RecyclerView.ViewHolder{
    TextView description;
    ImageView imagen;
    CardView cardView;

    public CustomViewHolderAds(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardView_ads);
        description = itemView.findViewById(R.id.textDescriptionAds);
        imagen = itemView.findViewById(R.id.imageAds);
    }
}
