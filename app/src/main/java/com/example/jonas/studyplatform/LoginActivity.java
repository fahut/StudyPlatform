package com.example.jonas.studyplatform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button tButton;
    Button sButton;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tButton = (Button) findViewById(R.id.btn_login);
        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText a = (EditText) findViewById(R.id.loginUsername);
                String str = a.getText().toString();

                EditText b = (EditText) findViewById(R.id.loginPassword);
                String strb = b.getText().toString();

                String password = helper.searchPass(str);

                if(strb.equals(password))
                {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("username",str);
                    startActivity(i);
                }
                else
                {
                    Toast pass1 = Toast.makeText(LoginActivity.this, "Username and password doesn't match", Toast.LENGTH_SHORT);
                    pass1.show();
                }
            }
        });


        sButton = (Button) findViewById(R.id.btn_signup);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(i);
            }
        });

    }



}
