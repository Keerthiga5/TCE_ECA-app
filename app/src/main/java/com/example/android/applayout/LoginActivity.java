package com.example.android.applayout;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.applayout.*;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private TextView signup,reset;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        auth = FirebaseAuth.getInstance();


       if (auth.getCurrentUser()!= null) {
           startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

       // setContentView(R.layout.activity_login);
        inputEmail = (EditText) findViewById(R.id.nssid);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        signup = (TextView) findViewById(R.id.signup);
        btnLogin = (Button) findViewById(R.id.signin);
        reset = (TextView) findViewById(R.id.forgotpassword);


//       auth = FirebaseAuth.getInstance();
       mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
               if (user != null) {
                    // User is signed in
                    Log.d("aaaa", "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                     //User is signed out
                    Log.d("aaaa", "onAuthStateChanged:signed_out");
                   // toastMessage("Successfully signed out.");
                }

            }
        };

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.GONE);
                               /* final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                                        R.style.Progress);
                                progressDialog.setIndeterminate(true);
                                progressDialog.setMessage("Logging In...");
                                progressDialog.show();

                                new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {


                                                progressDialog.dismiss();
                                            }
                                        }, 3000);*/

                                if (!task.isSuccessful()) {

                                    if (password.length() < 6) {
                                        Toast.makeText(LoginActivity.this, "Password is too small", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Authentication error", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    //mAuthListener = new FirebaseAuth.AuthStateListener() {
                                        //@Override
                                        //public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                      //      FirebaseUser user = firebaseAuth.getCurrentUser();
                                         //   if (user != null) {
                                                // User is signed in
                                                //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                                                //Toast.makeText(this,"Successfully signed in with: " + user.getEmail(),Toast.LENGTH_SHORT).show();
                                           // } else {
                                                // User is signed out
                                              //  Log.d(TAG, "onAuthStateChanged:signed_out");
                                               // toastMessage("Successfully signed out.");
                                        //    }
                                            // ...
                                        //}
                                    //};
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

    }
    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            auth.removeAuthStateListener(mAuthListener);
        }
    }
}
