package com.example.citasapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.controller.CustomAdapterAboutUs;
import com.example.citasapp.controller.FirebaseReferences;
import com.example.citasapp.data.Physiotherapist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AboutUsActivity extends AppCompatActivity {
    private List<Physiotherapist> listPhysiotherapist;
    private RecyclerView recyclerView;
    private CustomAdapterAboutUs customAdapterAboutUs;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        recyclerView = findViewById(R.id.recyclerListAboutUs);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        listPhysiotherapist = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(FirebaseReferences.PHYSIOTERAPIST_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    listPhysiotherapist.removeAll(listPhysiotherapist);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Physiotherapist physiotherapist = snapshot.getValue(Physiotherapist.class);
                        listPhysiotherapist.add(physiotherapist);
                    }
                    customAdapterAboutUs = new CustomAdapterAboutUs(listPhysiotherapist);
                    recyclerView.setAdapter(customAdapterAboutUs);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
