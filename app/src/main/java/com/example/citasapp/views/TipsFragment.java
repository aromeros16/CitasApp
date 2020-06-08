package com.example.citasapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.citasapp.R;
import com.example.citasapp.controller.FirebaseReferences;
import com.example.citasapp.data.Tips;
import com.example.citasapp.controller.CustomAdapterTips;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TipsFragment extends Fragment {

    private List<Tips> tipsList;
    private RecyclerView recyclerView;
    private CustomAdapterTips customAdapterTips;

    private DatabaseReference databaseReference;

    public TipsFragment(){

    }
    public TipsFragment(List<Tips> tipsList){
        this.tipsList = tipsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tips, container, false);

        recyclerView = view.findViewById(R.id.recyclerListTips);
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
    return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
