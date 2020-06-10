package com.example.citasapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.controller.CustomAdapterTips;
import com.example.citasapp.controller.FirebaseReferences;
import com.example.citasapp.data.Tips;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TipsActivity extends AppCompatActivity {

    private List<Tips> tipsList;
    private RecyclerView recyclerView;
    private CustomAdapterTips customAdapterTips;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        recyclerView = findViewById(R.id.recyclerListTips);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        tipsList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(FirebaseReferences.TIPS_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    tipsList.removeAll(tipsList);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Tips tips = snapshot.getValue(Tips.class);
                        tipsList.add(tips);
                    }
                    customAdapterTips = new CustomAdapterTips(tipsList);
                    recyclerView.setAdapter(customAdapterTips);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
