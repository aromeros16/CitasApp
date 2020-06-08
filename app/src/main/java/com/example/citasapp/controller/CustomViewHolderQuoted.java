package com.example.citasapp.controller;


import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.data.Quoted;

public class CustomViewHolderQuoted extends RecyclerView.ViewHolder {

    TextView time,date,state;
    CardView cardView;

    public CustomViewHolderQuoted(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardView_quote);
        time = itemView.findViewById(R.id.textTimeQuoted);
        state = itemView.findViewById(R.id.textStateQuoted);
        date = itemView.findViewById(R.id.textDateQuoted);
    }

    public void setQuoted(Quoted quoted) {
        time.setText(quoted.getTimeQuoted());
        date.setText(quoted.getDateQuoted());
        String stateAux = quoted.getState();
        if(stateAux.equals("Ocupado")){
            state.setTextColor(Color.RED);
            state.setText("Confirmado");
        }else if(stateAux.equals("Pendiente")){
            state.setTextColor(Color.YELLOW);
            state.setText(quoted.getState());
        }
    }
}
