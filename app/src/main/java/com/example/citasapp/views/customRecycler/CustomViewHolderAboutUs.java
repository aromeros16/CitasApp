package com.example.citasapp.views.customRecycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;

public class CustomViewHolderAboutUs extends RecyclerView.ViewHolder {

    TextView nombre;
    ImageView imagen;

    public CustomViewHolderAboutUs(@NonNull View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.textNameAboutUs);
        imagen = itemView.findViewById(R.id.imageAboutUs);

    }
}
