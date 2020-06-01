package com.example.citasapp.views.customRecycler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.data.Physiotherapist;

import java.util.List;

public class CustomAdapterAboutUs extends RecyclerView.Adapter<CustomViewHolderAboutUs> {

    private List<Physiotherapist> listPhysiotherapist;

    public CustomAdapterAboutUs(List<Physiotherapist> physiotherapist) {
        listPhysiotherapist = physiotherapist;

    }
    @NonNull
    @Override
    public CustomViewHolderAboutUs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolderAboutUs(
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_aboutus,
                        parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolderAboutUs holder, int position) {

        holder.nombre.setText(listPhysiotherapist.get(position).getName()+
                " " +listPhysiotherapist.get(position).getSurname1()+
                " " +listPhysiotherapist.get(position).getSurname2());

        String base64String  = listPhysiotherapist.get(position).getImage();
        String base64Image = base64String.split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imagen.setImageBitmap(decodedByte);


    }

    @Override
    public int getItemCount() {
        return listPhysiotherapist.size();
    }
}
