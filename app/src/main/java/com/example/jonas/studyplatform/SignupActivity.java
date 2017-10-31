package com.example.jonas.studyplatform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    Button newButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        newButton = (Button) findViewById(R.id.signUpButton);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText name = (EditText) findViewById(R.id.nameText);
                EditText email = (EditText) findViewById(R.id.emailText);
                EditText username = (EditText) findViewById(R.id.usernameText);
                EditText password = (EditText) findViewById(R.id.passwordText);
                EditText password2 = (EditText) findViewById(R.id.password2Text);

                String namestr = name.getText().toString();
                String emailstr = email.getText().toString();
                String usernamestr = username.getText().toString();
                String passwordstr = password.getText().toString();
                String password2str = password2.getText().toString();

                if (!passwordstr.equals(password2str)) {
                    Toast pass = Toast.makeText(SignupActivity.this, "Passwords don't match", Toast.LENGTH_SHORT);
                    pass.show();
                } else {
                    User u = new User();
                    u.setName(namestr);
                    u.setEmail(emailstr);
                    u.setUsername(usernamestr);
                    u.setPassword(passwordstr);

                    helper.insertUser(u);
                }


            }
        });
    }}

