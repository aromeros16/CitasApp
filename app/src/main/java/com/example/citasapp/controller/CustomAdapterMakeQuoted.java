package com.example.citasapp.controller;

import android.app.AlertDialog;import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.data.Quoted;
import com.example.citasapp.data.QuotedAux;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterMakeQuoted extends RecyclerView.Adapter<CustomViewHolderMakeQuoted> {

    private ArrayList<QuotedAux> quotedList;

    public CustomAdapterMakeQuoted(List<QuotedAux> quotedList) {
        this.quotedList = new ArrayList<>(quotedList);
    }

    @NonNull
    @Override
    public CustomViewHolderMakeQuoted onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_make_quote,
                parent, false);
        return new CustomViewHolderMakeQuoted(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolderMakeQuoted holder, final int position) {
        holder.setQuoted(quotedList.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quotedList.get(position).getState().equals("Libre")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setIcon(R.drawable.splash).setTitle("Confirmación cita").
                            setMessage("Dia: " + quotedList.get(position).getDateAux()
                                    + "\n" + "Hora: " + quotedList.get(position).getTime()).
                            setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                    DatabaseReference reference = firebaseDatabase.getReference(FirebaseReferences.QUOTEDS_REFERENCE);

                                    Quoted quoted = new Quoted();
                                    quoted.setIdUser(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                    quoted.setDateQuoted(quotedList.get(position).getDateAux());
                                    quoted.setTimeQuoted(quotedList.get(position).getTime());
                                    quoted.setState("Pendiente");

                                    reference.push().setValue(quoted);
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

                }
                else{
                    Toast.makeText(v.getContext(), "Debes seleccionar una hora que este libre", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return quotedList.size();
    }
}
