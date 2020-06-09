package com.example.citasapp.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.citasapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectInformationFragment extends Fragment {

    private ImageView imageView;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_information, container, false);

        imageView = view.findViewById(R.id.imageAboutUsSingle);
        textView = view.findViewById(R.id.textAboutUsSingle);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String image = bundle.get("image").toString();
            String description = bundle.get("description").toString();

            textView.setText(description);

            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
        }
        return view;
    }

}
