package com.example.jonas.studyplatform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button tButton;
    Button sButton;
    Button fButton;


    private EditText mEmailField;
    private EditText mPasswordField;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mEmailField = (EditText) findViewById(R.id.loginUsername);
        mPasswordField = (EditText) findViewById(R.id.loginPassword);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };



        tButton = (Button) findViewById(R.id.btn_login);
        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String email = mEmailField.getText().toString();
                String pass = mPasswordField.getText().toString();
                if(email.equals("") || pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this, R.string.failed_towrite,
                            Toast.LENGTH_SHORT).show();
                }

                else {
                    signIn(email, pass);
                }
            }
        });

        fButton = (Button) findViewById(R.id.forgotButton);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseAuth auth = FirebaseAuth.getInstance();
                final String emailAdd = mEmailField.getText().toString();

                if (!emailAdd.equals("")) {
                    auth.sendPasswordResetEmail(emailAdd)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, ""+ getString(R.string.forgot_mail) + " " + emailAdd,
                                                Toast.LENGTH_SHORT).show();
                                        Log.d(TAG, "Email sent.");
                                    }
                                }
                            });

                }
                else
                {
                    Toast.makeText(LoginActivity.this, R.string.forgot_blank,
                            Toast.LENGTH_SHORT).show();
                }
            }

        });


        sButton = (Button) findViewById(R.id.btn_signup);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, R.string.failed_login,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        }

                        // ...
                    }
                });



    }










}

