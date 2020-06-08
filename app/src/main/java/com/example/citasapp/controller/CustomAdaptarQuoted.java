package com.example.citasapp.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.data.Quoted;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdaptarQuoted extends RecyclerView.Adapter<CustomViewHolderQuoted> {

    private ArrayList<Quoted> quotedList;

    public CustomAdaptarQuoted(ArrayList<Quoted> quotedList) {
        this.quotedList = new ArrayList<>(quotedList);
    }

    @NonNull
    @Override
    public CustomViewHolderQuoted onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_quote,
                parent, false);

        return new CustomViewHolderQuoted(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolderQuoted holder, int position) {
        holder.setQuoted(quotedList.get(position));
        holder.cardView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setIcon(R.drawable.splash).setTitle("¿Desea anular su cita?").
                    setMessage("Dia: " + quotedList.get(position).getDateQuoted()
                            + "\n" + "Hora: " + quotedList.get(position).getTimeQuoted()).
                    setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference reference = firebaseDatabase.getReference(FirebaseReferences.QUOTEDS_REFERENCE);

                            reference.child(quotedList.get(position).getIdQuoted()).removeValue();
                            dialog.dismiss();
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return quotedList.size();
    }
}

