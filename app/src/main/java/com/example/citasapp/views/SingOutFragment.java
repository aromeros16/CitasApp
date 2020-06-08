package com.example.citasapp.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.citasapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class SingOutFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sing_out, container, false);
        FirebaseAuth.getInstance().signOut();


        return  view;
    }
}
