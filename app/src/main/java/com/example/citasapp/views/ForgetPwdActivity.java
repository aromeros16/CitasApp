package com.example.citasapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.citasapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPwdActivity extends AppCompatActivity {

    private Button btnFwdPwd;
    private EditText txtEmail;
    private String email ;

    private ProgressDialog mDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frw_password);

        btnFwdPwd = findViewById(R.id.butnForwardPsw);
        txtEmail = findViewById(R.id.textFwdPwd);

        mDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        btnFwdPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString();
                if (!email.isEmpty()){
                resertPassword();}
                else{
                    Toast.makeText(ForgetPwdActivity.this,"Debes ingresar una direccion de correo",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void resertPassword() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    mDialog.setMessage("Esepre unos segundos ......");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    Toast.makeText(ForgetPwdActivity.this,"Correo enviado, revise su buzón",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ForgetPwdActivity.this,"No se ha podido mandar el correo, no existe usuario con ese correo",Toast.LENGTH_SHORT).show();
                }
                mDialog.dismiss();
            }
        });

    }
}
