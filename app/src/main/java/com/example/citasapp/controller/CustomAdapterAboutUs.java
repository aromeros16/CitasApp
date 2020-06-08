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
import com.example.citasapp.data.Physiotherapist;
import com.example.citasapp.views.SelectInformationFragment;

import java.util.List;

public class CustomAdapterAboutUs extends RecyclerView.Adapter<CustomViewHolderAboutUs> {

    private List<Physiotherapist> listPhysiotherapist;

    public CustomAdapterAboutUs(List<Physiotherapist> physiotherapist) {
        listPhysiotherapist = physiotherapist;

    }
    @NonNull
    @Override
    public CustomViewHolderAboutUs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_aboutus,
                        parent, false);


        return new CustomViewHolderAboutUs(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolderAboutUs holder, final int position) {

        final String nameAux = listPhysiotherapist.get(position).getName()+
                " " +listPhysiotherapist.get(position).getSurname1()+
                " " +listPhysiotherapist.get(position).getSurname2();

        holder.nombre.setText(nameAux);

        final String base64String  = listPhysiotherapist.get(position).getImage();
        final String base64Image = base64String.split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imagen.setImageBitmap(decodedByte);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("name",nameAux);
                bundle.putString("image",base64Image);
                bundle.putString("description",listPhysiotherapist.get(position).getDescription());

                SelectInformationFragment selectInformationFragment = new SelectInformationFragment();
                selectInformationFragment.setArguments(bundle);

                String backStateName = selectInformationFragment.getClass().getName();

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentNavHost, selectInformationFragment)
                        .addToBackStack(backStateName).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhysiotherapist.size();
    }

}
