package com.example.citasapp.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.citasapp.R;
import com.example.citasapp.controller.FirebaseReferences;
import com.example.citasapp.data.Physiotherapist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

enum ProviderType {
    BASIC,
    GOOGLE
}

public class HomeFragment extends Fragment {

    TextView email,provider;
    Button btnQuouted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Inflamos un fragmento para poder asignarselo a la vista
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        email = view.findViewById(R.id.textEmail_home);
        provider = view.findViewById(R.id.textProvider_home);
        btnQuouted = view.findViewById(R.id.btonQuote);
        //Setup
        Intent intent = getActivity().getIntent();
        String email = intent.getStringExtra("email");
        String provider = intent.getStringExtra("provider");
        setup(email,provider);

        // Saved data
        SharedPreferences prefs = getActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("email",email);
        edit.putString("provider",provider);
        edit.commit();

        //Prueba insercion fisios
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference(FirebaseReferences.PHYSIOTERAPIST_REFERENCE);

        btnQuouted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Physiotherapist ph = new Physiotherapist(1,"paco@gmail.com","Paco","Perez","Perez","123456897",12345687,"Es un nuebo asda","data:image/jpeg;base64,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","Physio");
                databaseReference.push().setValue(ph);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void setup(String txtemail, String txtprovider){
        email.setText(txtemail);
        provider.setText(txtprovider);
    }
}
