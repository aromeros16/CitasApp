package com.example.citasapp.views.customRecycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;

public class CustomViewHolderAboutUs extends RecyclerView.ViewHolder {

    TextView nombre;
    ImageView imagen;
    CardView cardView;

    public CustomViewHolderAboutUs(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardView_aboutUs);
        nombre = itemView.findViewById(R.id.textNameAboutUs);
        imagen = itemView.findViewById(R.id.imageAboutUs);

    }
}
