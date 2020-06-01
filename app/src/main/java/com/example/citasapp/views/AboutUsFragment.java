package com.example.citasapp.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.citasapp.R;
import com.example.citasapp.controller.FirebaseReferences;
import com.example.citasapp.data.Physiotherapist;
import com.example.citasapp.views.customRecycler.CustomAdapterAboutUs;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AboutUsFragment extends Fragment{

    private List<Physiotherapist> listPhysiotherapist;
    private RecyclerView recyclerView;
    private CustomAdapterAboutUs customAdapterAboutUs;

    private DatabaseReference databaseReference;

    public AboutUsFragment() {

    }

    public AboutUsFragment(List<Physiotherapist> listPhysiotherapist) {
        this.listPhysiotherapist = listPhysiotherapist;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        recyclerView = view.findViewById(R.id.recyclerListAboutUs);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        listPhysiotherapist = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("physiotherapists").addValueEventListener(new ValueEventListener() {
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

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
