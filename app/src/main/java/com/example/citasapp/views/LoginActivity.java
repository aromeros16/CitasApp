package com.example.citasapp.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.LocusId;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.citasapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout txtEmail, txtPassword;
    private Button btnSingIn, btnForgetPwd, btnRegister;
    private ImageButton btnGmail;
    private String emailPrefs, providerPrefs;

    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;

    private int RC_SING_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Ocultar actionbar
        getSupportActionBar().hide();

        session();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.textEmail_Login);
        txtPassword = findViewById(R.id.textPassword_Login);
        btnRegister = findViewById(R.id.btonRegister_Login);
        btnSingIn = findViewById(R.id.btonSignIn_Login);
        btnGmail = findViewById(R.id.btonGmail_login);


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
                            //Toast.makeText(LoginActivity.this,"Error en el login, Por favor prueba otra vez",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            showHome(task.getResult().getUser().getEmail(),ProviderType.BASIC);
                        }
                    }
                });
            }
            else{
                Toast.makeText(LoginActivity.this,"Error Occurrido en la autenticación!",Toast.LENGTH_SHORT).show();
            }
        }
    });



    btnGmail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            signInGmail();
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
        Intent intentHome = new Intent(LoginActivity.this,HomeActivity.class);
        intentHome.putExtra("email",email);
        intentHome.putExtra("provider",provider.name());
        startActivity(intentHome);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    private void signInGmail(){
        //Config
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Sistema autenticacion
        googleSignInClient = GoogleSignIn.getClient(this,gso);
        googleSignInClient.signOut();
        //Mostrar pantalla autenticación google
        startActivityForResult(googleSignInClient.getSignInIntent(), RC_SING_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

          if (requestCode == RC_SING_IN) {
              Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
              handleSignInResult(task);
          }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completeTask){
        try {
            GoogleSignInAccount googleSignInAccount = completeTask.getResult(ApiException.class);
            Toast.makeText(LoginActivity.this,"Registro correctamente",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(googleSignInAccount);
        }
        catch (ApiException e){
            Toast.makeText(LoginActivity.this,"Registro incorrectamente",Toast.LENGTH_SHORT).show();
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
                    showHome(account.getEmail(),ProviderType.GOOGLE);
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

    private void session() {
        SharedPreferences prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        emailPrefs = prefs.getString("email",null);
        providerPrefs = prefs.getString("provider","null");

        Log.i("","alcaparras");
        //Log.i("",emailPrefs);
        if(emailPrefs != null ){
            showHome(emailPrefs,ProviderType.valueOf(providerPrefs));
        }
    }
}
