package com.example.citasapp.views.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout txtEmail, txtPassword;
    private Button btnSingIn, btnForgetPwd, btnRegister, btnGmail;
    private FirebaseAuth.AuthStateListener authStateListener;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Ocultar actionbar
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.textEmail_Login);
        txtPassword = findViewById(R.id.textPassword_Login);
        btnRegister = findViewById(R.id.btonRegister_Login);
        btnSingIn = findViewById(R.id.btonSignIn_Login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    Toast.makeText(LoginActivity.this,"Registro correcto",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Por favor, debe registrarse",Toast.LENGTH_SHORT).show();
                }
            }
        };

    btnSingIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = txtEmail.getEditText().getText().toString();
            String pwd = txtEmail.getEditText().getText().toString();
            if(email.isEmpty()){
                txtEmail.setError("Please enter email id");
                txtEmail.requestFocus();
            }
            else  if(pwd.isEmpty()){
                txtPassword.setError("Please enter your password");
                txtPassword.requestFocus();
            }
            else  if(email.isEmpty() && pwd.isEmpty()){
                Toast.makeText(LoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
            }
            else  if(!(email.isEmpty() && pwd.isEmpty())){
                firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intToHome = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intToHome);
                        }
                    }
                });
            }
            else{
                Toast.makeText(LoginActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

            }
        }
    });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }


}
