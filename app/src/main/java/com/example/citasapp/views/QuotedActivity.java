package com.example.citasapp.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.controller.CustomAdaptarQuoted;
import com.example.citasapp.controller.FirebaseReferences;
import com.example.citasapp.data.Quoted;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuotedActivity extends AppCompatActivity {

    private TextView txtDate, txtTime, txtState;
    private ArrayList<Quoted> quotedList;
    private RecyclerView recyclerView;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quoted);

        txtDate = findViewById(R.id.textDateQuoted);
        txtTime = findViewById(R.id.textTimeQuoted);
        txtState = findViewById(R.id.textStateQuoted);

        uploadQuoted();
    }
    private void uploadQuoted() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child(FirebaseReferences.QUOTEDS_REFERENCE);



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                quotedList = new ArrayList<>();
                userID = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                if (dataSnapshot.exists()) {
                    quotedList.removeAll(quotedList);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String dateBase = snapshot.child("idUser").getValue(String.class);
                        if (userID.equals(dateBase)){
                            Quoted quoted = snapshot.getValue(Quoted.class);
                            quoted.setIdQuoted(snapshot.getKey());
                            quotedList.add(quoted);
                        }
                    }

                    recyclerView = findViewById(R.id.recyclerviewQuoted);
                    recyclerView.setAdapter(new CustomAdaptarQuoted(quotedList));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("", "Failed to read value.", databaseError.toException());
            }
        });
    }
}

