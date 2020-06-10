package com.example.citasapp.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.data.Tips;
import com.example.citasapp.views.SelectInformationActivity;

import java.util.List;

public class CustomAdapterTips extends RecyclerView.Adapter<CustomViewHolderTips> {

    private List<Tips> tipsList;
    private Context context;

    public CustomAdapterTips(List<Tips> list){
        tipsList = list;
    }

    @NonNull
    @Override
    public CustomViewHolderTips onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tips,
                parent, false);
        context = parent.getContext();
        return new CustomViewHolderTips(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolderTips holder, final int position) {

        holder.description.setText(tipsList.get(position).getDescription());

        final String base64String  = tipsList.get(position).getImage();
        final String base64Image = base64String.split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imagen.setImageBitmap(decodedByte);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SelectInformationActivity.class);
                intent.putExtra("name","null");
                intent.putExtra("image",base64Image);
                intent.putExtra("description",tipsList.get(position).getDescription());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }
}
