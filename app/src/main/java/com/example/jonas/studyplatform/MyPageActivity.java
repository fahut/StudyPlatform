package com.example.jonas.studyplatform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPageActivity extends AppCompatActivity {

    Button updateButton;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    DatabaseReference userRef;
    ValueEventListener ev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setIcon(R.mipmap.imageedit_1_9052204102);

        myRef = FirebaseDatabase.getInstance().getReference().child("users");

        mAuth = FirebaseAuth.getInstance();

        updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyPageActivity.this, ChangeMyPageActivity.class);
                startActivity(i);
            }

        });
                // For at få adgang til databasen. EditUser er sendt til databasen, og mypage henter denne data.
                //Realtime database, datasnapshot, kigger på data hele tiden.
        userRef = myRef.child(mAuth.getCurrentUser().getUid());

        ev = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                
                User currentUser = dataSnapshot.getValue(User.class);


                TextView tv1 = (TextView) findViewById(R.id.displayNameTextView);
                TextView tv3 = (TextView) findViewById(R.id.displayEducationTextView);
                TextView tv4 = (TextView) findViewById(R.id.displayUsernameTextView);
                TextView tv5 = (TextView) findViewById(R.id.displayStrong1TextView);
                TextView tv6 = (TextView) findViewById(R.id.displayStrong2TextView);
                TextView tv7 = (TextView) findViewById(R.id.displayWeak1TextView);
                TextView tv8 = (TextView) findViewById(R.id.displayWeak2TextView);

                tv1.setText(getString(R.string.name) + ": " + currentUser.getName());
                tv3.setText(getString(R.string.edu)+ ": " + currentUser.getEducation());
                tv4.setText(getString(R.string.username) + ": " + currentUser.getUsername());
                tv5.setText(getString(R.string.strong)+ ": " + currentUser.getStrong1());
                tv6.setText(getString(R.string.strong)+ ": " + currentUser.getStrong2());
                tv7.setText(getString(R.string.weak)+ ": " + currentUser.getWeak1());
                tv8.setText(getString(R.string.weak)+ ": " + currentUser.getWeak2());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        userRef.addValueEventListener(ev);

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(MyPageActivity.this, Settings.class);
                startActivity(intent);
                return true;

            case R.id.message:
                Intent intent1 = new Intent(MyPageActivity.this, MessageActivity.class);
                startActivity(intent1);
                return true;

            case R.id.search:
                Intent intent2 = new Intent(MyPageActivity.this, Search.class);
                startActivity(intent2);
                return true;

            case R.id.home:
                Intent intent3 = new Intent(MyPageActivity.this, MainActivity.class);
                startActivity(intent3);
                return true;

            case R.id.signoff:
                Intent intent4 = new Intent(MyPageActivity.this, SignOffActivity.class);
                startActivity(intent4);
                return true;

            case R.id.about:
                Intent intent6 = new Intent(MyPageActivity.this, AboutActivity.class);
                startActivity(intent6);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }




}
