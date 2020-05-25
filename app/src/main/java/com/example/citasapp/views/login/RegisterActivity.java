package com.example.citasapp.views.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.citasapp.R;
import com.example.citasapp.views.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {


    private TextInputLayout txtEmail, txtPwd, txtRepPwd;
    private Button btonSignUp;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Ocultar actionbar
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.textEmail);
        txtPwd = findViewById(R.id.textPassword);
        txtRepPwd = findViewById(R.id.textRepPassword);
        btonSignUp = findViewById(R.id.btonRegister);

        btonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getEditText().getText().toString();
                String pwd = txtPwd.getEditText().getText().toString();
                String repPwd = txtRepPwd.getEditText().getText().toString();

                if (email.isEmpty()) {
                    txtEmail.setError("Por favor introduce un correo");
                    txtEmail.requestFocus();
                } else if (!emailValid(email)) {
                    txtEmail.setError("Por favor introduce un correo correcto");
                    txtEmail.requestFocus();
                } else if (pwd.isEmpty()) {
                    txtPwd.setError("Por favor introduce una contraseña");
                    txtPwd.requestFocus();
                } else if (!pwd.equals(repPwd)) {
                    txtPwd.setError("Las contraseñas deben coincidir");
                    txtEmail.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "No has introducido ningun campo", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    firebaseAuth.getInstance().createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {

                                Toast.makeText(RegisterActivity.this, "Registro fallido, Por favor prueba otra vez", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "Error Ocurrido!", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private boolean emailValid(String email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        } else {
            txtEmail.setError(null);
        }
        return true;
    }
}
