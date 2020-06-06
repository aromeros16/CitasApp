package com.example.citasapp.views.customRecycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;

public class CustomViewHolderTips extends RecyclerView.ViewHolder {

    TextView description;
    ImageView imagen;
    CardView cardView;

    public CustomViewHolderTips(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardView_tips);
        description = itemView.findViewById(R.id.textDescriptionTips);
        imagen = itemView.findViewById(R.id.imageTips);
    }
}
