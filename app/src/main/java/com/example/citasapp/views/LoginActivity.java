package com.example.citasapp.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.citasapp.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout txtEmail, txtPassword;
    private Button btnSingIn, btnForgetPwd, btnRegister;
    private ImageButton btnGmail;
    private String emailPrefs, providerPrefs;

    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;

    private int RC_SING_IN = 100;
    private static final String GOOGLE = "google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Ocultar actionbar
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.textEmail_Login);
        txtPassword = findViewById(R.id.textPassword_Login);
        btnRegister = findViewById(R.id.btonRegister_Login);
        btnSingIn = findViewById(R.id.btonSignIn_Login);
        btnGmail = findViewById(R.id.btonGmail_login);
        btnForgetPwd = findViewById(R.id.btonForgetPwd_Login);


        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else{
                  startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setTosUrl("http://databaseremote.esy.es/RegisterLite/html/privacidad.html")
                            .setAvailableProviders(Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build()))
                            .build(),RC_SING_IN);
                    Toast.makeText(LoginActivity.this,"Por favor, debe registrarse",Toast.LENGTH_SHORT).show();
                }
            }
        };

    btnSingIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = txtEmail.getEditText().getText().toString();
            String pwd = txtPassword.getEditText().getText().toString();

            if(email.isEmpty()){
                txtEmail.setError("Por favor introduzca un email");
                txtEmail.requestFocus();
            }
            else if(pwd.isEmpty()){
                txtPassword.setError("Tienes que introducir la contraseña");
                txtPassword.requestFocus();
            }
            else if(email.isEmpty() && pwd.isEmpty()){
                Toast.makeText(LoginActivity.this,"Campos obligatorios vacios!",Toast.LENGTH_SHORT).show();
            }
            else if(!(email.isEmpty() && pwd.isEmpty())){
                firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            showAlert();
                        }
                        else{
                            showHome();
                        }
                    }
                });
            }
            else{
                Toast.makeText(LoginActivity.this,"Error Occurrido en la autenticación!",Toast.LENGTH_SHORT).show();
            }
        }
    });

btnForgetPwd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, ForgetPwdActivity.class);
        startActivity(intent);
    }
});

    btnGmail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            signInGmail();
        }
    });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (authStateListener != null){
         //   firebaseAuth.addAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //firebaseAuth.addAuthStateListener(authStateListener);
    }

    private void showAlert(){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("Error");
        build.setMessage("Se ha producido un error autenticando al usuario");
        build.setPositiveButton("Aceptar",null);
        AlertDialog dialog = build.create();
        dialog.show();
    }

    private void showHome(){
        Intent intentHome = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intentHome);
    }

    private void signInGmail(){
        //Config
        AuthUI.IdpConfig googleIdp = new AuthUI.IdpConfig.GoogleBuilder()
                                                .build();

        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                .setTosUrl("http://databaseremote.esy.es/RegisterLite/html/privacidad.html")
                .setAvailableProviders(Arrays.asList(googleIdp))
                .build(),RC_SING_IN);
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

          if (requestCode == RC_SING_IN) {
              if (resultCode == RESULT_OK) {
                  Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                  handleSignInResult(task);
              }
          }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completeTask){
        try {
            GoogleSignInAccount googleSignInAccount = completeTask.getResult(ApiException.class);
            Toast.makeText(LoginActivity.this,"Registro correcto",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(googleSignInAccount);
        }
        catch (ApiException e){
            Toast.makeText(LoginActivity.this,"Algo falló, intente de nuevo",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(final GoogleSignInAccount account) {
        if (account != null) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    showHome();
                } else {
                    showAlert();
                }
            }
        });
        }
        else{
            Toast.makeText(LoginActivity.this, "No tiene cuenta de Gmail en este dispositivo", Toast.LENGTH_SHORT).show();
        }
    }
}
