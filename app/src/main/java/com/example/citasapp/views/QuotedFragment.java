package com.example.citasapp.views;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.controller.FirebaseReferences;
import com.example.citasapp.data.Quoted;
import com.example.citasapp.views.customRecycler.CustomAdaptarQuoted;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class QuotedFragment extends Fragment {

    private TextView txtDate, txtTime, txtState;
    private List<Quoted> quotedList;
    private RecyclerView recyclerView;
    private CustomAdaptarQuoted customAdaptarQuoted;

    private String userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quoted, container, false);

        txtDate = view.findViewById(R.id.textDateQuoted);
        txtTime = view.findViewById(R.id.textTimeQuoted);
        txtState = view.findViewById(R.id.textStateQuoted);

        uploadQuoted();

        return view;
    }

    private void uploadQuoted() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child(FirebaseReferences.QUOTEDS_REFERENCE);

        quotedList = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userID = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                if (dataSnapshot.exists()) {
                    quotedList.removeAll(quotedList);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                       Quoted quoted = snapshot.getValue(Quoted.class);
                       quotedList.add(quoted);
               }

                    //customAdaptarQuoted = new CustomAdaptarQuoted(quotedList);
                    //recyclerView.setAdapter(customAdaptarQuoted);
        }
}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("", "Failed to read value.", databaseError.toException());
            }
        });
    }
}
