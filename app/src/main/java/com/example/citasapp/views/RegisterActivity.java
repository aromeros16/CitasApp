package com.example.citasapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.citasapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

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
        txtEmail = findViewById(R.id.textEmail_home);
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
                    txtEmail.setError("Por favor introduce un email");
                    txtEmail.requestFocus();
                } else if (!emailValid(email)) {
                    txtEmail.setError("Formato de email incorrecto");
                    txtEmail.requestFocus();
                } else if (pwd.isEmpty()) {
                    txtPwd.setError("Por favor introduce una contraseña");
                    txtPwd.requestFocus();
                }else if (repPwd.isEmpty()) {
                        txtPwd.setError("Por favor introduce la confirmacion de la contraseña");
                        txtPwd.requestFocus();
                } else if (!pwd.equals(repPwd)) {
                    txtPwd.setError("Las contraseñas deben coincidir");
                    txtEmail.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Campos obligatorios incompletos", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                               /* if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }*/
                                showAlert();
                            } else {
                                showHome(task.getResult().getUser().getEmail(),ProviderType.BASIC);
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "Error Occurrido en el registro!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showAlert(){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("Error");
        build.setMessage("Se ha producido un error autenticando al usuario");
        build.setPositiveButton("Aceptar",null);
        AlertDialog dialog = build.create();
        dialog.show();
    }

    private void showHome(String email, ProviderType provider){
        Intent intentHome = new Intent(this,HomeActivity.class);
        intentHome.putExtra("email",email);
        intentHome.putExtra("provider",provider.name());
        startActivity(intentHome);
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
