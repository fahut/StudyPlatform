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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    Button signUp;
    EditText emailText;
    EditText passwordText;
    EditText confirmPasswordText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference userRef;

    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUp = (Button) findViewById(R.id.signUpButton);
        emailText = (EditText) findViewById(R.id.emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        confirmPasswordText = (EditText) findViewById(R.id.confirmPasswordText);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String email = emailText.getText().toString();
                String pass = passwordText.getText().toString();
                String conPass = confirmPasswordText.getText().toString();

                if(email.equals("") || pass.equals("") || conPass.equals(""))
                {
                    Toast.makeText(SignupActivity.this, R.string.empty_fields,
                            Toast.LENGTH_SHORT).show();
                }

                else if(!pass.equals(conPass))
                {

                    Toast.makeText(SignupActivity.this, R.string.password_not,
                            Toast.LENGTH_SHORT).show();

                    passwordText.setText("");
                    confirmPasswordText.setText("");
                }

                else
                {
                    createAccount(email, pass);

                    Toast.makeText(SignupActivity.this, R.string.success_create,
                            Toast.LENGTH_SHORT).show();
                }



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

    public void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);


        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, R.string.failed_tosignup,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            userRef = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid());
                            userRef.child("education").setValue("");
                            userRef.child("email").setValue("");
                            userRef.child("name").setValue("");
                            userRef.child("strong1").setValue("");
                            userRef.child("strong2").setValue("");
                            userRef.child("username").setValue("");
                            userRef.child("weak1").setValue("");
                            userRef.child("weak2").setValue("");



                        }


                        // ...
                    }
                });
    }

}

