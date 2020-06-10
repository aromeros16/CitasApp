package com.example.citasapp.views;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citasapp.R;
import com.example.citasapp.controller.CustomAdapterMakeQuoted;
import com.example.citasapp.controller.FirebaseReferences;
import com.example.citasapp.data.QuotedAux;
import com.example.citasapp.views.Dialog.DatePickerFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MakeQuotedActivity extends AppCompatActivity {

    private TextView txtDate;
    private List<QuotedAux> list;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_quoted);

        //Hooks
        txtDate = findViewById(R.id.txtDate_Quoted);

        Calendar c = new GregorianCalendar();

        String mes = Integer.toString(c.get(Calendar.MONTH));
        int mesAux = Integer.parseInt(mes);
        mesAux = mesAux+1;
        mes = Integer.toString(mesAux);

        String dateAct = c.get(Calendar.DAY_OF_MONTH) + "/"
                + mes +"/"
                + c.get(Calendar.YEAR);

        txtDate.setText(dateAct);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }
    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getSupportFragmentManager(), "Date Picker");
    }
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String dateAux = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1)
                    + "/" + String.valueOf(year);

            txtDate.setText(dateAux);

            Date parseDateAux = parseDate(dateAux);

            if (verifyDate(parseDateAux)){
                uploadQuoted(dateAux);}
            else{
                Toast.makeText(view.getContext(), "Debes ingresar una fecha que no sea pasada ", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private boolean verifyDate(Date dateAux) {
        //Parametro para calcular la diferencia de dias
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

        //Capturamos la fecha actual del sistema
        Date fechaActual = new Date();

        //Realizamos la operacion para saber que dia es superior actual
        long deltaDaysActual = (dateAux.getTime() - fechaActual.getTime())/MILLSECS_PER_DAY;

        if (deltaDaysActual > -1){
            return true;
        }
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewMakeQuoted);
        recyclerView.setAdapter(new CustomAdapterMakeQuoted(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        return false;
    }

    private void uploadQuoted(final String dateAux) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child(FirebaseReferences.QUOTEDS_REFERENCE);

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                list.add(new QuotedAux("10:00 - 11:00","Libre",dateAux));
                list.add(new QuotedAux("11:00 - 12:00","Libre",dateAux));
                list.add(new QuotedAux("12:00 - 13:00","Libre",dateAux));
                list.add(new QuotedAux("13:00 - 14:00","Libre",dateAux));
                list.add(new QuotedAux("17:00 - 18:00","Libre",dateAux));
                list.add(new QuotedAux("18:00 - 19:00","Libre",dateAux));

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String dateBase = snapshot.child("dateQuoted").getValue(String.class);

                    if (dateBase.equals(dateAux)){
                        String timeBase = snapshot.child("timeQuoted").getValue(String.class);

                        QuotedAux quotedAux = new QuotedAux();
                        quotedAux.setTime(timeBase);
                        quotedAux.setDateAux(dateBase);
                        quotedAux.setState("Libre");

                        int aux = 0;
                        for (QuotedAux quotedList : list){
                            if (quotedAux.getDateAux().equals(quotedList.getDateAux()) &&
                                    quotedAux.getTime().equals(quotedList.getTime())){
                                list.get(aux).setState("Ocupado");
                            }
                            aux++;
                        }
                    }
                }

                recyclerView = findViewById(R.id.recyclerviewMakeQuoted);
                recyclerView.setAdapter(new CustomAdapterMakeQuoted(list));
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public static Date parseDate(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        }
        catch (ParseException ex)
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
}