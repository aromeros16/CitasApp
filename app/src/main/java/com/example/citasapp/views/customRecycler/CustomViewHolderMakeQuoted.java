package com.example.citasapp.views.customRecycler;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.data.QuotedAux;

public class CustomViewHolderMakeQuoted extends RecyclerView.ViewHolder {

    private TextView time,state;
    public CardView cardView;

    public CustomViewHolderMakeQuoted(View view) {
        super(view);
        time = view.findViewById(R.id.textTime);
        state = view.findViewById(R.id.textState);
        cardView = view.findViewById(R.id.cardView_make_quote);
    }

    public void setQuoted(QuotedAux quoted) {
        time.setText(quoted.getTime());
        String stateAux = quoted.getState();
        if (stateAux.equals("Libre")){
            state.setTextColor(Color.GREEN);
            state.setText(quoted.getState());
        }else if(stateAux.equals("Ocupado")){
            state.setTextColor(Color.RED);
            state.setText(quoted.getState());
        }
    }
}
