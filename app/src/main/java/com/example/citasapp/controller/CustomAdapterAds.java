package com.example.citasapp.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.data.Ads;
import com.example.citasapp.views.SelectInformationFragment;

import java.util.List;

public class CustomAdapterAds extends RecyclerView.Adapter<CustomViewHolderTips> {

    private List<Ads> adsList;

    public CustomAdapterAds(List<Ads> list){
        adsList = list;
    }

    @NonNull
    @Override
    public CustomViewHolderTips onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ad,
                parent, false);

        return new CustomViewHolderTips(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolderTips holder, final int position) {

        holder.description.setText(adsList.get(position).getDescription());

        final String base64String  = adsList.get(position).getImage();
        final String base64Image = base64String.split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imagen.setImageBitmap(decodedByte);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("image",base64Image);
                bundle.putString("description",adsList.get(position).getDescription());

                SelectInformationFragment selectInformationFragment = new SelectInformationFragment();
                selectInformationFragment.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentNavHost, selectInformationFragment)
                        .addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }
}
